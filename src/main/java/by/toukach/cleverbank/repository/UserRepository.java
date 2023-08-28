package by.toukach.cleverbank.repository;

import by.toukach.cleverbank.dao.User;

public interface UserRepository {

  User create(User user);

  User read(Long userId);

  User readByLogin(String login);

  boolean isExists(String login);
}
