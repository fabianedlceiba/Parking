package co.com.ceiba.parking.exceptions;

public class NoAvailableDayException extends RuntimeException {

  private static final long serialVersionUID = -7816045910381524555L;

  public NoAvailableDayException(String message) {
    super(message);
  }

}
