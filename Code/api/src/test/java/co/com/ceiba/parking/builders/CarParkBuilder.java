package co.com.ceiba.parking.builders;

import java.time.LocalDateTime;

import co.com.ceiba.parking.domain.CarPark;
import co.com.ceiba.parking.domain.Vehicle;
import co.com.ceiba.parking.enums.EVehicleType;

/**
 * Builder for {@link CarPark}.
 * 
 * @author fabian.diaz
 *
 */
public final class CarParkBuilder {

  private long id;
  private Vehicle vehicle;
  private LocalDateTime entryDate;
  private LocalDateTime exitDate;
  private short slotNumber;
  private String notes;

  public CarParkBuilder withMotorcycle(String plate, Short cylinder) {
    this.vehicle = new Vehicle(plate, EVehicleType.MOTORCYCLE);
    this.vehicle.setCylinder(cylinder);
    return this;
  }

  public CarParkBuilder withCar(String plate) {
    this.vehicle = new Vehicle(plate, EVehicleType.CAR);
    return this;
  }

  public CarParkBuilder withEntryDate(LocalDateTime entryDate) {
    this.entryDate = entryDate;
    return this;
  }

  public CarParkBuilder withExitDate(LocalDateTime exitDate) {
    this.exitDate = exitDate;
    return this;
  }

  public CarParkBuilder withSlotNumber(short slotNumber) {
    this.slotNumber = slotNumber;
    return this;
  }

  public CarParkBuilder withNotes(String notes) {
    this.notes = notes;
    return this;
  }

  public CarPark build() {
    CarPark carPark = new CarPark();
    carPark.setId(id);
    carPark.setVehicle(vehicle);
    carPark.setEntryDate(entryDate);
    carPark.setExitDate(exitDate);
    carPark.setSlotNumber(slotNumber);
    carPark.setNotes(notes);
    return carPark;
  }

}
