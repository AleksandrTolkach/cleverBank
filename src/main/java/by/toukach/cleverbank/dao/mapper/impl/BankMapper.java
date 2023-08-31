package by.toukach.cleverbank.dao.mapper.impl;

import by.toukach.cleverbank.dao.Bank;
import by.toukach.cleverbank.dao.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankMapper implements RowMapper<Bank> {

  private static final RowMapper<Bank> instance = new BankMapper();

  @Override
  public Bank mapRow(ResultSet resultSet) throws SQLException {
    return Bank.builder()
        .id(resultSet.getLong("id"))
        .createdAt(resultSet.getObject("created_at", LocalDateTime.class))
        .name(resultSet.getString("name"))
        .build();
  }

  public static RowMapper<Bank> getInstance() {
    return instance;
  }
}
