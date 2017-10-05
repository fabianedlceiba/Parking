package co.com.ceiba.parking.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import co.com.ceiba.parking.enums.EVehicleType;

@Embeddable
public class DbVehicle implements Serializable {

  private static final long serialVersionUID = 325846355113872729L;

  @Column(name = "Plate", nullable = false, length = 6)
  private String plate;

  @Column(name = "Type", nullable = false)
  @Enumerated(EnumType.ORDINAL)
  private EVehicleType type;

  @Column(name = "Cylinder")
  private Short cylinder;

  public DbVehicle() {
  }

  public DbVehicle(String plate, EVehicleType type) {
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

}
