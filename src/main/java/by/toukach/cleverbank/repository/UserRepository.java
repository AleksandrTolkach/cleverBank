package by.toukach.cleverbank.repository;

import by.toukach.cleverbank.dao.User;

/**
 * Интерфейс для выполнения запросов, связанных с пользователями, в базу.
 * */
public interface UserRepository {

  /**
   * Метод для создания пользователя в базе.
   *
   * @param user создаваемый пользователь.
   * @return созданный пользователь.
   */
  User create(User user);

  /**
   * Метод для чтения пользователя из базы.
   *
   * @param userId id запрашиваемого пользователя.
   * @return запрашиваемый пользователь.
   */
  User read(Long userId);

  /**
   * Метод для чтения пользователя из базы по логину.
   *
   * @param login логин запрашиваемого пользователя.
   * @return запрашиваемый пользователь.
   */
  User readByLogin(String login);

  /**
   * Метод для проверки существования пользователя в базе по логину.
   *
   * @param login логин для проверки.
   * @return возвращает true, если пользователь существует, и false, если отсутствует.
   */
  boolean isExists(String login);
}
