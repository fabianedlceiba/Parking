package co.com.ceiba.parking.builders;

import java.time.LocalDateTime;

import co.com.ceiba.parking.entities.DbCarPark;
import co.com.ceiba.parking.entities.DbVehicle;
import co.com.ceiba.parking.enums.EVehicleType;

/**
 * Builder for {@link DbCarPark}.
 * 
 * @author fabian.diaz
 *
 */
public final class DbCarParkBuilder {

  private long id;
  private DbVehicle vehicle;
  private LocalDateTime entryDate;
  private LocalDateTime exitDate;
  private short slotNumber;
  private String notes;

  public DbCarParkBuilder() {
    this.entryDate = LocalDateTime.now();
  }

  public DbCarParkBuilder withId(long id) {
    this.id = id;
    return this;
  }

  public DbCarParkBuilder withCar(String plate) {
    this.vehicle = new DbVehicle(plate, EVehicleType.CAR);
    return this;
  }

  public DbCarParkBuilder withMotorcycle(String plate, Short cylinder) {
    this.vehicle = new DbVehicle(plate, EVehicleType.MOTORCYCLE);
    this.vehicle.setCylinder(cylinder);
    return this;
  }

  public DbCarParkBuilder withEntryDate(LocalDateTime entryDate) {
    this.entryDate = entryDate;
    return this;
  }

  public DbCarParkBuilder withExitDate(LocalDateTime exitDate) {
    this.exitDate = exitDate;
    return this;
  }

  public DbCarParkBuilder withSlotNumber(short slotNumber) {
    this.slotNumber = slotNumber;
    return this;
  }

  public DbCarParkBuilder withNotes(String notes) {
    this.notes = notes;
    return this;
  }

  public DbCarPark build() {
    DbCarPark dbCarPark = new DbCarPark();
    dbCarPark.setId(id);
    dbCarPark.setVehicle(vehicle);
    dbCarPark.setEntryDate(entryDate);
    dbCarPark.setExitDate(exitDate);
    dbCarPark.setSlotNumber(slotNumber);
    dbCarPark.setNotes(notes);
    return dbCarPark;
  }

}
