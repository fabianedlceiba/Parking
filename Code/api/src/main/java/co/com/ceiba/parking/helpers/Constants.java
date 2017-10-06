package co.com.ceiba.parking.helpers;

public final class Constants {

  public static final int MAX_CARS = 20;
  public static final int MAX_MOTORCYCLE = 10;

  public static final int MAX_HOURS = 9;
  public static final int MOTORCYCLE_ADITIONAL = 2000;
  public static final int MAX_CILYNDER = 500;

  public static final String NO_AVAILABLE_DAYS = "El vehiculo no puede ingresar porque no es día habil.";
  public static final String LIMIT_EXCEEDED_CAR = "El parqueadero ya está lleno para carros.";
  public static final String LIMIT_EXCEEDED_MOTORCYCLE = "El parqueadero ya está lleno para motos.";
  public static final String PLATE_NOT_FOUND = "El vehiculo no se encuentra estacionado.";
  public static final String PLATE_EMPTY = "La placa no puede ser nula o vacia.";

  private Constants() {
    throw new IllegalStateException("Utility class");
  }

}
