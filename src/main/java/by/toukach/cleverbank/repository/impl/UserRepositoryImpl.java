package by.toukach.cleverbank.repository.impl;

import by.toukach.cleverbank.dao.User;
import by.toukach.cleverbank.dao.mapper.RowMapper;
import by.toukach.cleverbank.dao.mapper.impl.UserMapper;
import by.toukach.cleverbank.exception.DBException;
import by.toukach.cleverbank.exception.EntityNotFoundException;
import by.toukach.cleverbank.repository.UserRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                    + "VALUES (?, ?, ?, ?, ?) RETURNING ID")) {

      statement.setObject(1, user.getCreatedAt());
      statement.setString(2, user.getLogin());
      statement.setString(3, user.getPassword());
      statement.setString(4, user.getFirstname());
      statement.setString(5, user.getLastname());

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

  @Override
  public boolean isExists(String login) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement statement =
            connection.prepareStatement("SELECT EXISTS (SELECT 1 FROM application.users "
                + "WHERE login = ?)")) {

      statement.setString(1, login);

      ResultSet resultSet = statement.executeQuery();

      if (!resultSet.wasNull() && resultSet.next()) {
        return resultSet.getBoolean(1);
      } else {
        throw new DBException("Не удалось выполнить запрос в базу");
      }
    } catch (SQLException e) {
      throw new DBException("Не удалось выполнить запрос в базу", e);
    }
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
        throw new EntityNotFoundException(String.format("Пользователь с %s %s не найден",
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
