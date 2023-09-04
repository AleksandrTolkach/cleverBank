package by.toukach.cleverbank.exception;

/**
 * Класс представляющий исключение, выбрасываемое при некорректной остановке потока.
 * */
public class SleepException extends RuntimeException {

  public SleepException(String message, Throwable cause) {
    super(message, cause);
  }
}
