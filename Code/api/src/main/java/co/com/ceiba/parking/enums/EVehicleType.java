package co.com.ceiba.parking.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/***
 * Enum with vehicle type.
 * 
 * @author fabian.diaz
 *
 */
public enum EVehicleType {

  CAR(0, 1000, 8000), MOTORCYCLE(1, 500, 6000);

  private final int id;
  private final int hour;
  private final int day;

  EVehicleType(int id, int hour, int day) {
    this.id = id;
    this.hour = hour;
    this.day = day;
  }

  @JsonValue
  public int getId() {
    return this.id;
  }

  /**
   * Value per hour.
   * 
   * @return Value per hour.
   */
  public int getHour() {
    return this.hour;
  }

  /**
   * Value per day.
   * 
   * @return Value per day.
   */
  public int getDay() {
    return this.day;
  }
}
