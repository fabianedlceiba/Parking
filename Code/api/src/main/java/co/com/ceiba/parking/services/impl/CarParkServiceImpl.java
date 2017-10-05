package co.com.ceiba.parking.services.impl;

import static co.com.ceiba.parking.helpers.Constants.MAX_CARS;
import static co.com.ceiba.parking.helpers.Constants.MAX_MOTORCYCLE;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parking.domain.CarPark;
import co.com.ceiba.parking.domain.Vehicle;
import co.com.ceiba.parking.entities.DbCarPark;
import co.com.ceiba.parking.enums.EVehicleType;
import co.com.ceiba.parking.exceptions.LimitExceededException;
import co.com.ceiba.parking.exceptions.NoAvailableDayException;
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

    LocalDateTime now = carpark.getEntryDate();

    DayOfWeek day = now.getDayOfWeek();

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

    DbCarPark entity = carParkRepository.save(carpark.toEntity());
    carpark.setId(entity.getId());

    return carpark;
  }
}
