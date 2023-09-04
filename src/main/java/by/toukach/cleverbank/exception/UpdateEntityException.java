package by.toukach.cleverbank.exception;

/**
 * Класс представляющий исключение, выбрасываемое при обновлении сущности.
 * */
public class UpdateEntityException extends RuntimeException {

  public UpdateEntityException(String message) {
    super(message);
  }
}
