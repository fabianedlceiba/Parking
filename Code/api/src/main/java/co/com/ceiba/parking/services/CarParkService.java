package co.com.ceiba.parking.services;

import java.time.LocalDateTime;

import co.com.ceiba.parking.domain.CarPark;

public interface CarParkService {

  /**
   * 
   * @param carpark
   * @return
   */
  CarPark park(CarPark carpark);

  /**
   * 
   * @param plate
   * @param exitDate
   * @return
   */
  Long unpark(String plate, LocalDateTime exitDate);

}
