package by.toukach.cleverbank.repository.impl;

import by.toukach.cleverbank.dao.Account;
import by.toukach.cleverbank.dao.mapper.RowMapper;
import by.toukach.cleverbank.dao.mapper.impl.AccountMapper;
import by.toukach.cleverbank.exception.DBException;
import by.toukach.cleverbank.repository.AccountRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class AccountRepositoryImpl implements AccountRepository {

  private static final AccountRepository instance = new AccountRepositoryImpl();

  private final DataSource dataSource;
  private final RowMapper<Account> accountRowMapper;

  private AccountRepositoryImpl() {
    dataSource = DBInitializerImpl.getInstance().getDataSource();
    accountRowMapper = AccountMapper.getInstance();
  }

  @Override
  public Account create(Account account, Long userId) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO application.accounts (created_at, updated_at, title, sum, user_id) "
                + "VALUES (now(), now(), ?, ?, ?) RETURNING id")) {
      statement.setString(1, account.getTitle());
      statement.setLong(2, account.getSum());
      statement.setLong(3, userId);

      statement.execute();

      ResultSet generatedKeys = statement.getGeneratedKeys();

      if (generatedKeys.next()) {
        account.setId(generatedKeys.getLong("id"));
      }
    } catch (SQLException e) {
      throw new DBException("Не удалось записать счет в базу", e);
    }
    return account;
  }

  @Override
  public Account read(Long id) {
    return readAccountsIfExists("id", id).get(0);
  }

  @Override
  public List<Account> readByUserId(Long userId) {
    return readAccountsIfExists("user_id", userId);
  }

  private List<Account> readAccountsIfExists(String argumentName, Object argumentValue) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(
            "SELECT id, created_at, updated_at, title, sum, user_id "
                + "FROM application.accounts "
                + "WHERE " + argumentName + "= ?")) {

      statement.setObject(1, argumentValue);

      ResultSet resultSet = statement.executeQuery();
      List<Account> accounts = new ArrayList<>();
      if (!resultSet.wasNull() && resultSet.next()) {
        accounts.add(accountRowMapper.mapRow(resultSet));
      }
      return accounts;
    } catch (SQLException e) {
      throw new DBException(String.format("Не удалось считать счет с %s %s из БД",
          argumentName, argumentValue), e);
    }
  }

  public static AccountRepository getInstance() {
    return instance;
  }
}
