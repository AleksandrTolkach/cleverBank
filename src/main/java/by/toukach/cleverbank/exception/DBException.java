package by.toukach.cleverbank.exception;

public class DBException extends RuntimeException {

  public DBException(String message, Throwable cause) {
    super(message, cause);
  }
}
