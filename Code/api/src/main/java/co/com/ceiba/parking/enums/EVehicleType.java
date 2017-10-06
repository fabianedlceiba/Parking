package co.com.ceiba.parking.enums;

/***
 * Enum with vehicle type.
 * 
 * @author fabian.diaz
 *
 */
public enum EVehicleType {

  CAR(1000, 8000), MOTORCYCLE(500, 6000);

  private final int hour;
  private final int day;

  EVehicleType(int hour, int day) {
    this.hour = hour;
    this.day = day;
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
