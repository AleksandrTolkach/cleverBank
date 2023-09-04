package by.toukach.cleverbank.exception;

/**
 * Класс представляющий исключение, выбрасываемое в случае отсутствия запрашиваемой сущности в базе.
 * */
public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(String message) {
    super(message);
  }
}
