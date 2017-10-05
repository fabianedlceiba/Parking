package co.com.ceiba.parking.services.impl;

import static co.com.ceiba.parking.helpers.Constants.MAX_CARS;
import static co.com.ceiba.parking.helpers.Constants.MAX_MOTORCYCLE;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parking.domain.CarPark;
import co.com.ceiba.parking.domain.Vehicle;
import co.com.ceiba.parking.entities.DbCarPark;
import co.com.ceiba.parking.enums.EVehicleType;
import co.com.ceiba.parking.exceptions.LimitExceededException;
import co.com.ceiba.parking.exceptions.NoAvailableDayException;
import co.com.ceiba.parking.exceptions.NotFoundException;
import co.com.ceiba.parking.helpers.Constants;
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
      throw new NoAvailableDayException(Constants.NO_AVAILABLE_DAYS);
    }

    EVehicleType carType = vehicle.getType();
    int count = carParkRepository.countByVehicleTypeAndExitDateIsNull(carType);

    if (carType == EVehicleType.CAR && count >= MAX_CARS) {
      throw new LimitExceededException(Constants.LIMIT_EXCEEDED_CAR);
    }
    else if (carType == EVehicleType.MOTORCYCLE && count >= MAX_MOTORCYCLE) {
      throw new LimitExceededException(Constants.LIMIT_EXCEEDED_MOTORCYCLE);
    }
  }

  @Override
  public Long unpark(String plate, LocalDateTime exitDate) {

    DbCarPark carPark = carParkRepository.findByVehiclePlateAndExitDateIsNull(plate)
        .orElseThrow(() -> new NotFoundException(Constants.PLATE_NOT_FOUND));

    Long result = calculate(carPark, exitDate);

    carPark.setExitDate(exitDate);
    carParkRepository.save(carPark);

    return result;
  }

  private Long calculate(DbCarPark carPark, LocalDateTime exitDate) {
    EVehicleType type = carPark.getVehicle().getType();
    Duration duration = Duration.between(carPark.getEntryDate(), exitDate);
    long hours = duration.toHours();

    Long result;
    if (hours <= Constants.MAX_HOURS) {
      result = hours * type.getHour();
    }
    else {

      int elapsedDays = (int) (hours / 24);

      result = (elapsedDays * type.getDay()) + (hours % 24) * type.getHour();
    }

    if (type == EVehicleType.MOTORCYCLE && carPark.getVehicle().getCylinder() > 500) {
      result += Constants.MOTORCYCLE_ADITIONAL;
    }

    return result;
  }
}
