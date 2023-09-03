package by.toukach.cleverbank.repository.impl;

import by.toukach.cleverbank.dao.Account;
import by.toukach.cleverbank.dao.mapper.RowMapper;
import by.toukach.cleverbank.dao.mapper.impl.AccountMapper;
import by.toukach.cleverbank.exception.DBException;
import by.toukach.cleverbank.exception.EntityNotFoundException;
import by.toukach.cleverbank.exception.ExceptionMessage;
import by.toukach.cleverbank.repository.AccountRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        PreparedStatement statement =
            connection.prepareStatement(
                "INSERT INTO application.accounts (created_at, updated_at, title, bank_id, "
                    + "sum, user_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
      statement.setObject(1, account.getCreatedAt());
      statement.setObject(2, account.getUpdatedAt());
      statement.setString(3, account.getTitle());
      statement.setLong(4, account.getBankId());
      statement.setDouble(5, account.getSum());
      statement.setLong(6, userId);

      statement.execute();

      ResultSet generatedKeys = statement.getGeneratedKeys();

      if (generatedKeys.next()) {
        account.setId(generatedKeys.getLong("id"));
      }
    } catch (SQLException e) {
      throw new DBException(ExceptionMessage.ACCOUNT_SAVE_MESSAGE, e);
    }
    return account;
  }

  @Override
  public Account read(Long id) {
    List<Account> accounts = readAccountsIfExists("id", id);
    if (!accounts.isEmpty()) {
      return accounts.get(0);
    } else {
      throw new EntityNotFoundException(
          String.format(ExceptionMessage.ACCOUNT_NOT_FOUND_MESSAGE, id));
    }
  }

  @Override
  public List<Account> readByUserId(Long userId) {
    return readAccountsIfExists("user_id", userId);
  }

  @Override
  public List<Account> readAll() {
    try (Connection connection = DBInitializerImpl.getInstance().getDataSource().getConnection();
        PreparedStatement statement = connection.prepareStatement(
            "SELECT id, created_at, updated_at, title, bank_id, sum, user_id "
                + "FROM application.accounts "
        )) {

      ResultSet resultSet = statement.executeQuery();

      List<Account> accounts = new ArrayList<>();
      while (resultSet.next()) {
        accounts.add(accountRowMapper.mapRow(resultSet));
      }

      return accounts;
    } catch (SQLException e) {
      throw new DBException(ExceptionMessage.DB_REQUEST_MESSAGE);
    }
  }

  @Override
  public Account update(Account account, Connection connection) {
    Long id = account.getId();

    try (PreparedStatement statement =
        connection.prepareStatement(
            "UPDATE application.accounts SET updated_at = ?, title = ?, sum = ? "
                + "WHERE id = ?")) {
      statement.setObject(1, account.getUpdatedAt());
      statement.setString(2, account.getTitle());
      statement.setDouble(3, account.getSum());
      statement.setLong(4, id);

      int updatedRows = statement.executeUpdate();

      if (updatedRows != 0) {
        Connection[] connections = new Connection[] {connection};
        return readAccountsIfExists("id", id, connections).get(0);
      } else {
        throw new EntityNotFoundException(String.format(ExceptionMessage.ACCOUNT_NOT_FOUND_MESSAGE, id));
      }
    } catch (SQLException e) {
      throw new DBException(ExceptionMessage.ACCOUNT_UPDATE_MESSAGE, e);
    }
  }

  private List<Account> readAccountsIfExists(
      String argumentName, Object argumentValue, Connection... existingConnection) {
    boolean isExistingConnectionNotExists =
        existingConnection == null || existingConnection.length == 0;

    try {
      Connection connection =
          isExistingConnectionNotExists ? dataSource.getConnection() : existingConnection[0];
      PreparedStatement statement =
          connection.prepareStatement(
              "SELECT id, created_at, updated_at, title, bank_id, sum, user_id "
                  + "FROM application.accounts "
                  + "WHERE "
                  + argumentName
                  + "= ?");

      statement.setObject(1, argumentValue);

      ResultSet resultSet = statement.executeQuery();
      List<Account> accounts = new ArrayList<>();
      while (resultSet.next()) {
        accounts.add(accountRowMapper.mapRow(resultSet));
      }

      if (isExistingConnectionNotExists) {
        connection.close();
      }
      return accounts;
    } catch (SQLException e) {
      throw new DBException(
          String.format(ExceptionMessage.SPECIFIC_ACCOUNT_NOT_FOUND_MESSAGE,
              argumentName, argumentValue), e);
    }
  }

  public static AccountRepository getInstance() {
    return instance;
  }
}
