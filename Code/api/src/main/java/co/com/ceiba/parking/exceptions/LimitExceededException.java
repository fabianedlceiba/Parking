package co.com.ceiba.parking.exceptions;

public class LimitExceededException extends RuntimeException {

  private static final long serialVersionUID = -1528370539989043087L;

  public LimitExceededException(String message) {
    super(message);
  }

}
