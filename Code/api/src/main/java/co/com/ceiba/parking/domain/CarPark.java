/**
 * 
 */
package co.com.ceiba.parking.domain;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.com.ceiba.parking.entities.DbCarPark;

/**
 * Domain bean of the entity {@link DbCarPark}.
 * 
 * @author fabian.diaz
 *
 */
public final class CarPark {

  private long id;

  @NotNull(message = "El vehiculo no puede ser nulo")
  private Vehicle vehicle;
  private LocalDateTime entryDate;
  private LocalDateTime exitDate;
  private short slotNumber;
  private String notes;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public LocalDateTime getEntryDate() {
    return entryDate;
  }

  public void setEntryDate(LocalDateTime entryDate) {
    this.entryDate = entryDate;
  }

  public LocalDateTime getExitDate() {
    return exitDate;
  }

  public void setExitDate(LocalDateTime exitDate) {
    this.exitDate = exitDate;
  }

  public short getSlotNumber() {
    return slotNumber;
  }

  public void setSlotNumber(short slotNumber) {
    this.slotNumber = slotNumber;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  /**
   * Convert domain bean to entity.
   * 
   * @return Entity bean.
   */
  @JsonIgnore
  public DbCarPark toEntity() {
    DbCarPark dbCarPark = new DbCarPark();
    dbCarPark.setId(getId());
    dbCarPark.setVehicle(getVehicle() == null ? null : getVehicle().toEntity());
    dbCarPark.setEntryDate(getEntryDate());
    dbCarPark.setExitDate(getExitDate());
    dbCarPark.setSlotNumber(getSlotNumber());
    dbCarPark.setNotes(getNotes());
    return dbCarPark;
  }

}
