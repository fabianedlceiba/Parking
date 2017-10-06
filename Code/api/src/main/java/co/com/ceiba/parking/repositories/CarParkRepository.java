package co.com.ceiba.parking.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parking.entities.DbCarPark;
import co.com.ceiba.parking.enums.EVehicleType;

@Repository
public interface CarParkRepository extends CrudRepository<DbCarPark, Long> {

  /**
   * Find the vehicle park with a specific plate.
   * 
   * @param plate
   *          Plate of the vehicle.
   * @return Information about park of the vehicle.
   */
  Optional<DbCarPark> findByVehiclePlateAndExitDateIsNull(String plate);

  /**
   * Count the vehicles parks.
   * 
   * @param type
   *          Type of the vehicle.
   * @return Number of the vehicles park.
   */
  int countByVehicleTypeAndExitDateIsNull(EVehicleType type);

  /**
   * Finds all vehicles that is in the car park.
   * 
   * @return All vehicles.
   */
  List<DbCarPark> findByExitDateIsNull();

}
