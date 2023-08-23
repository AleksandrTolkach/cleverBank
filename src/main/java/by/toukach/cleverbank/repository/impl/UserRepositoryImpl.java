package by.toukach.cleverbank.repository.impl;

import by.toukach.cleverbank.dao.User;
import by.toukach.cleverbank.dao.mapper.RowMapper;
import by.toukach.cleverbank.dao.mapper.impl.UserMapper;
import by.toukach.cleverbank.exception.DBException;
import by.toukach.cleverbank.exception.UserNotFoundException;
import by.toukach.cleverbank.repository.UserRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class UserRepositoryImpl implements UserRepository {

  private static final UserRepository instance = new UserRepositoryImpl();

  private final DataSource dataSource;
  private final RowMapper<User> userRowMapper;

  private UserRepositoryImpl() {
    dataSource = DBInitializerImpl.getInstance().getDataSource();
    userRowMapper = UserMapper.getInstance();
  }

  @Override
  public User create(User user) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement statement =
            connection.prepareStatement(
                "INSERT INTO application.users (created_at, login, password, "
                    + "firstname, lastname) \n"
                    + "VALUES (now(), ?, ?, ?, ?) RETURNING id")) {

      statement.setString(1, user.getLogin());
      statement.setString(2, user.getPassword());
      statement.setString(3, user.getFirstname());
      statement.setString(4, user.getLastname());

      statement.execute();

      ResultSet generatedKeys = statement.getResultSet();

      if (generatedKeys.next()) {
        user.setId(generatedKeys.getLong("id"));
      }
    } catch (SQLException e) {
      throw new DBException("Не удалось записать пользователя в базу", e);
    }
    return user;
  }

  @Override
  public User read(Long userId) {
    return readUserIfExists("id", userId);
  }

  @Override
  public User readByLogin(String login) {
    return readUserIfExists("login", login);
  }

  private User readUserIfExists(String argumentName, Object argumentValue) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(
            "SELECT id, created_at, login, password, firstname, lastname "
                + "FROM application.users "
                + "WHERE " + argumentName + "= ?")) {

      statement.setObject(1, argumentValue);

      ResultSet resultSet = statement.executeQuery();
      if (!resultSet.wasNull() && resultSet.next()) {
        return userRowMapper.mapRow(resultSet);
      } else {
        throw new UserNotFoundException(String.format("Пользователь с %s %s не найден",
            argumentName, argumentValue));
      }
    } catch (SQLException e) {
      throw new DBException(String.format("Не удалось считать пользователя c %s %s из БД",
          argumentName, argumentValue), e);
    }
  }

  public static UserRepository getInstance() {
    return instance;
  }
}
