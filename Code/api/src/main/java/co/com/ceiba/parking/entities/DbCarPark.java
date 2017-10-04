package co.com.ceiba.parking.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import co.com.ceiba.parking.enums.EVehicleType;

@Entity
@Table(name = "Car_Park")
public class DbCarPark implements Serializable {

  private static final long serialVersionUID = -2415121613973011481L;

  @Id
  @Column(name = "Id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Embedded
  private DbVehicle vehicle;

  @Column(name = "EntryDate", nullable = false)
  @Type(type = "org.hibernate.type.LocalDateTimeType")
  private LocalDateTime entryDate;

  @Column(name = "ExitDate")
  @Type(type = "org.hibernate.type.LocalDateTimeType")
  private LocalDateTime exitDate;

  @Column(name = "SlotNumber", nullable = false)
  private short slotNumber;

  @Column(name = "Notes", length = 100)
  private String notes;

  public DbCarPark() {
  }

  public DbCarPark(long id) {
    this.id = id;
  }

  public DbCarPark(String plate, EVehicleType type, LocalDateTime entryDate, short slotNumber) {
    this.entryDate = entryDate;
    this.slotNumber = slotNumber;
    this.vehicle = new DbVehicle(plate, type);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public DbVehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(DbVehicle vehicle) {
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

}
