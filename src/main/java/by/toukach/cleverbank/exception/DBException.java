package by.toukach.cleverbank.exception;

/**
 * Класс представляющий исключение, выбрасываемое при выполнении соединения к базе, отправки запросов.
 * */
public class DBException extends RuntimeException {

  public DBException(String message) {
    super(message);
  }

  public DBException(String message, Throwable cause) {
    super(message, cause);
  }
}
