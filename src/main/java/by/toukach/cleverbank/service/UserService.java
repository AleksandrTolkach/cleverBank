package by.toukach.cleverbank.service;

import by.toukach.cleverbank.dto.UserDto;

/**
 * Интерфейс для выполнения операция с пользователями.
 * */
public interface UserService {

  /**
   * Метод для создания пользователя.
   *
   * @param userDto создаваемый пользователь.
   * @return созданный пользователь.
   */
  UserDto create(UserDto userDto);

  /**
   * Метод для чтения пользователя.
   *
   * @param userId id запрашиваемого пользователя.
   * @return запрашиваемый пользователь.
   */
  UserDto read(Long userId);

  /**
   * Метод для чтения пользователя по логину.
   *
   * @param login логин запрашиваемого пользователя.
   * @return запрашиваемый пользователь.
   */
  UserDto readByLogin(String login);

  /**
   * Метод для проверки существования пользователя.
   *
   * @param login логин для проверки.
   * @return возвращает true, если пользователь существует, и false, если нет.
   */
  boolean isExists(String login);
}
