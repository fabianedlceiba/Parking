package co.com.ceiba.parking.services;

import java.time.LocalDateTime;
import java.util.List;

import co.com.ceiba.parking.domain.CarPark;

public interface CarParkService {

  /**
   * Parks any vehicle.
   * 
   * @param carpark
   *          Information of the vehicle.
   * @return {@link CarPark}
   */
  CarPark park(CarPark carpark);

  /**
   * Unpark any vehicle.
   * 
   * @param plate
   *          Plate of the vehicle.
   * @param exitDate
   *          Exit date.
   * @return Amount to paid.
   */
  Long unpark(String plate, LocalDateTime exitDate);

  /**
   * Gets all vehicles park.
   * 
   * @return All vehicles.
   */
  List<CarPark> getAllParkedVehicles();

}
