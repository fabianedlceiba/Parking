package co.com.ceiba.parking.services.impl;

import static co.com.ceiba.parking.helpers.Constants.LIMIT_EXCEEDED_CAR;
import static co.com.ceiba.parking.helpers.Constants.LIMIT_EXCEEDED_MOTORCYCLE;
import static co.com.ceiba.parking.helpers.Constants.MAX_CARS;
import static co.com.ceiba.parking.helpers.Constants.MAX_CILYNDER;
import static co.com.ceiba.parking.helpers.Constants.MAX_HOURS;
import static co.com.ceiba.parking.helpers.Constants.MAX_MOTORCYCLE;
import static co.com.ceiba.parking.helpers.Constants.MOTORCYCLE_ADITIONAL;
import static co.com.ceiba.parking.helpers.Constants.NO_AVAILABLE_DAYS;
import static co.com.ceiba.parking.helpers.Constants.PLATE_NOT_FOUND;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parking.domain.CarPark;
import co.com.ceiba.parking.domain.Vehicle;
import co.com.ceiba.parking.entities.DbCarPark;
import co.com.ceiba.parking.enums.EVehicleType;
import co.com.ceiba.parking.exceptions.LimitExceededException;
import co.com.ceiba.parking.exceptions.NoAvailableDayException;
import co.com.ceiba.parking.exceptions.NotFoundException;
import co.com.ceiba.parking.repositories.CarParkRepository;
import co.com.ceiba.parking.services.CarParkService;

@Service
public class CarParkServiceImpl implements CarParkService {

  private static final String LETTER_A = "A";

  @Autowired
  private CarParkRepository carParkRepository;

  @Override
  public CarPark park(CarPark carpark) {

    canPark(carpark);

    DbCarPark entity = carParkRepository.save(carpark.toEntity());
    carpark.setId(entity.getId());

    return carpark;
  }

  /**
   * Validate if the vehicule can park.
   * 
   * @param carpark
   *          Vehicle.
   */
  private void canPark(CarPark carpark) {

    DayOfWeek day = carpark.getEntryDate().getDayOfWeek();
    Vehicle vehicle = carpark.getVehicle();

    if (vehicle.getPlate().startsWith(LETTER_A) && !(day == MONDAY || day == SUNDAY)) {
      throw new NoAvailableDayException(NO_AVAILABLE_DAYS);
    }

    EVehicleType carType = vehicle.getType();
    int count = carParkRepository.countByVehicleTypeAndExitDateIsNull(carType);

    if (carType == EVehicleType.CAR && count >= MAX_CARS) {
      throw new LimitExceededException(LIMIT_EXCEEDED_CAR);
    }
    else if (carType == EVehicleType.MOTORCYCLE && count >= MAX_MOTORCYCLE) {
      throw new LimitExceededException(LIMIT_EXCEEDED_MOTORCYCLE);
    }
  }

  @Override
  public Long unpark(String plate, LocalDateTime exitDate) {

    DbCarPark carPark = carParkRepository.findByVehiclePlateAndExitDateIsNull(plate).orElseThrow(() -> new NotFoundException(PLATE_NOT_FOUND));

    Long result = calculateAmountToBePaid(carPark, exitDate);

    carPark.setExitDate(exitDate);
    carParkRepository.save(carPark);

    return result;
  }

  /**
   * Calculate amount to be paid.
   * 
   * @param carPark
   *          Vehicle.
   * @param exitDate
   *          Exit date.
   * @return Amount to be paid.
   */
  private Long calculateAmountToBePaid(DbCarPark carPark, LocalDateTime exitDate) {

    EVehicleType type = carPark.getVehicle().getType();
    Duration duration = Duration.between(carPark.getEntryDate(), exitDate);

    int hours = (int) Math.ceil((double) duration.toMinutes() / 60);

    Long result;
    if (hours <= MAX_HOURS) {
      result = (long) (hours * type.getHour());
    }
    else {

      int elapsedDays = hours / 24;

      result = (long) (elapsedDays * type.getDay()) + ((hours % 24) * type.getHour());
    }

    if (type == EVehicleType.MOTORCYCLE && carPark.getVehicle().getCylinder() > MAX_CILYNDER) {
      result += MOTORCYCLE_ADITIONAL;
    }

    return result;
  }

  @Override
  public List<CarPark> getAllParkedVehicles() {
    List<DbCarPark> vehicles = carParkRepository.findByExitDateIsNull();
    List<CarPark> result = new ArrayList<>(vehicles.size());
    for (DbCarPark dbCarPark : vehicles) {
      CarPark carPack = new CarPark();
      carPack.fromEntity(dbCarPark);
      result.add(carPack);
    }
    return result;
  }

}
