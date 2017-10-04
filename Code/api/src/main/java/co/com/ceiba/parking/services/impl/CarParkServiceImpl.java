package co.com.ceiba.parking.services.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parking.domain.CarPark;
import co.com.ceiba.parking.domain.Vehicle;
import co.com.ceiba.parking.entities.DbCarPark;
import co.com.ceiba.parking.enums.EVehicleType;
import co.com.ceiba.parking.exceptions.LimitExceededException;
import co.com.ceiba.parking.exceptions.NoAvailableDayException;
import co.com.ceiba.parking.repositories.CarParkRepository;
import co.com.ceiba.parking.services.CarParkService;

@Service
public class CarParkServiceImpl implements CarParkService {

  private static final String LETTER_A = "A";

  @Autowired
  private CarParkRepository carParkRepository;

  @Override
  public long park(CarPark carpark) {

    DayOfWeek day = LocalDate.now().getDayOfWeek();

    Vehicle vehicle = carpark.getVehicle();

    if (vehicle.getPlate().startsWith(LETTER_A) && !(day == DayOfWeek.MONDAY || day == DayOfWeek.SUNDAY)) {
      throw new NoAvailableDayException("El vehiculo no puede ingresar porque no es día habil");
    }

    EVehicleType carType = vehicle.getType();
    int count = carParkRepository.countByVehicleTypeAndExitDateIsNull(carType);

    if (carType == EVehicleType.CAR && count >= 20) {
      throw new LimitExceededException("El parqueadero ya está lleno para carros.");
    }
    else if (carType == EVehicleType.MOTORCYCLE && count >= 10) {
      throw new LimitExceededException("El parqueadero ya está lleno para motos.");
    }

    DbCarPark entity = carpark.toEntity();
    carParkRepository.save(entity);

    return entity.getId();
  }

  @Override
  public boolean unPark() {
    return false;
  }

}
