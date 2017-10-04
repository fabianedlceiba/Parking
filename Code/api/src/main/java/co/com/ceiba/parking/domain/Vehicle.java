package co.com.ceiba.parking.domain;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.com.ceiba.parking.entities.DbVehicle;
import co.com.ceiba.parking.enums.EVehicleType;

public final class Vehicle {

  @NotBlank
  private String plate;

  private EVehicleType type;
  private Short cylinder;

  public Vehicle() {
  }

  public Vehicle(String plate, EVehicleType type) {
    this.plate = plate;
    this.type = type;
  }

  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public EVehicleType getType() {
    return type;
  }

  public void setType(EVehicleType type) {
    this.type = type;
  }

  public Short getCylinder() {
    return cylinder;
  }

  public void setCylinder(Short cylinder) {
    this.cylinder = cylinder;
  }

  @JsonIgnore
  public DbVehicle toEntity() {
    DbVehicle dbVehicle = new DbVehicle();
    dbVehicle.setPlate(getPlate());
    dbVehicle.setType(getType());
    dbVehicle.setCylinder(getCylinder());
    return dbVehicle;
  }

  public void fromEntity(DbVehicle entity) {
    setPlate(entity.getPlate());
    setType(entity.getType());
    setCylinder(entity.getCylinder());
  }

}
