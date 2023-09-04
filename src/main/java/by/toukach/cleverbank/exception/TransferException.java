package by.toukach.cleverbank.exception;

/**
 * Класс представляющий исключение, выбрасываемое при некорректном переводе средств между счетами.
 * */
public class TransferException extends RuntimeException {

  public TransferException(String message) {
    super(message);
  }
}
