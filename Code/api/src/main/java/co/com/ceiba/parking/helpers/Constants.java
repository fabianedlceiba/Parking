package co.com.ceiba.parking.helpers;

public final class Constants {

  public static final int MAX_CARS = 20;
  public static final int MAX_MOTORCYCLE = 10;
  public static final String NO_AVAILABLE_DAYS = "El vehiculo no puede ingresar porque no es día habil.";
  public static final String LIMIT_EXCEEDED_CAR = "El parqueadero ya está lleno para carros.";
  public static final String LIMIT_EXCEEDED_MOTORCYCLE = "El parqueadero ya está lleno para motos.";

  private Constants() {
    throw new IllegalStateException("Utility class");
  }

}
