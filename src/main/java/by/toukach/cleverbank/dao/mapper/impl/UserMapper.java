package by.toukach.cleverbank.dao.mapper.impl;

import by.toukach.cleverbank.dao.User;
import by.toukach.cleverbank.dao.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Класс для создания User DAO из ResultSet.
 * */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements RowMapper<User> {

  private static final RowMapper<User> instance = new UserMapper();

  @Override
  public User mapRow(ResultSet resultSet) throws SQLException {
    return User.builder()
        .id(resultSet.getLong("id"))
        .createdAt(resultSet.getObject("created_at", LocalDateTime.class))
        .login(resultSet.getString("login"))
        .password(resultSet.getString("password"))
        .firstname(resultSet.getString("firstname"))
        .lastname(resultSet.getString("lastname"))
        .build();
  }

  public static RowMapper<User> getInstance() {
    return instance;
  }
}
