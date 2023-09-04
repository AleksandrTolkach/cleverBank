package by.toukach.cleverbank.exception;

/**
 * Класс представляющий исключение, выбрасываемое при регистрации пользователя с уже существующим логином.
 * */
public class UserDuplicateException extends RuntimeException {

  public UserDuplicateException(String message) {
    super(message);
  }
}
