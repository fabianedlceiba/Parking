package co.com.ceiba.parking.exceptions;

public class NotFoundException extends RuntimeException {

  private static final long serialVersionUID = -597994229072829072L;

  public NotFoundException(String message) {
    super(message);
  }

}
