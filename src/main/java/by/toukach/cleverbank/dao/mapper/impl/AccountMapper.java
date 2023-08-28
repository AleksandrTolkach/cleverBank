package by.toukach.cleverbank.dao.mapper.impl;

import by.toukach.cleverbank.dao.Account;
import by.toukach.cleverbank.dao.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountMapper implements RowMapper<Account> {

  private static final RowMapper<Account> instance = new AccountMapper();

  @Override
  public Account mapRow(ResultSet resultSet) throws SQLException {
    return Account.builder()
        .id(resultSet.getLong("id"))
        .createdAt(resultSet.getObject("created_at", LocalDateTime.class))
        .updatedAt(resultSet.getObject("updated_at", LocalDateTime.class))
        .title(resultSet.getString("title"))
        .bankId(resultSet.getLong("bank_id"))
        .sum(resultSet.getLong("sum"))
        .build();
  }

  public static RowMapper<Account> getInstance() {
    return instance;
  }
}
