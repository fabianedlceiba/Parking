package co.com.ceiba.parking.domain;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.com.ceiba.parking.entities.DbVehicle;
import co.com.ceiba.parking.enums.EVehicleType;

public final class Vehicle {

  @NotNull(message = "La placa no puede ser nula.")
  private String plate;
  @NotNull(message = "El tipo de vehiculo no puede ser nulo.")
  private EVehicleType type;
  private Short cylinder;

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

}
