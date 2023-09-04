package by.toukach.cleverbank.exception;

/**
 * Класс представляющий исключение, выбрасываемое во время формирования чека.
 * */
public class CheckException extends RuntimeException {

  public CheckException(String message, Throwable cause) {
    super(message, cause);
  }
}
