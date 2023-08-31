package by.toukach.cleverbank.repository.impl;

import by.toukach.cleverbank.dao.Transaction;
import by.toukach.cleverbank.dao.mapper.RowMapper;
import by.toukach.cleverbank.dao.mapper.impl.TransactionMapper;
import by.toukach.cleverbank.exception.DBException;
import by.toukach.cleverbank.exception.EntityNotFoundException;
import by.toukach.cleverbank.repository.TransactionRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

public class TransactionRepositoryImpl implements TransactionRepository {

  private static final TransactionRepository instance = new TransactionRepositoryImpl();

  private final DataSource dataSource;
  private final RowMapper<Transaction> transactionRowMapper;

  private TransactionRepositoryImpl() {
    dataSource = DBInitializerImpl.getInstance().getDataSource();
    transactionRowMapper = TransactionMapper.getInstance();
  }

  @Override
  public Transaction create(Transaction transaction) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement statement =
            connection.prepareStatement(
                "INSERT INTO application.transactions (date, type, sender_bank_id, "
                    + "receiver_bank_id, sender_account_id, receiver_account_id, value) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

      statement.setObject(1, transaction.getDate());
      statement.setString(2, transaction.getType().name());
      statement.setLong(3, transaction.getSenderBankId());
      statement.setLong(4, transaction.getReceiverBankId());
      statement.setLong(5, transaction.getSenderAccountId());
      statement.setLong(6, transaction.getReceiverAccountId());
      statement.setLong(7, transaction.getValue());

      statement.execute();

      ResultSet generatedKeys = statement.getGeneratedKeys();

      if (generatedKeys.next()) {
        transaction.setId(generatedKeys.getLong("id"));
      }
    } catch (SQLException e) {
      throw new DBException("Не удалось записать транзакцию в базу", e);
    }
    return transaction;
  }

  @Override
  public Transaction read(Long id) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement statement =
            connection.prepareStatement(
                "SELECT id, date, type, sender_bank_id, receiver_bank_id, sender_account_id "
                    + "receiver_account_id, value "
                    + "FROM application.transactions "
                    + "WHERE id = ?")) {

      statement.setLong(1, id);

      ResultSet resultSet = statement.executeQuery();

      if (!resultSet.wasNull() && resultSet.next()) {
        return transactionRowMapper.mapRow(resultSet);
      } else {
        throw new EntityNotFoundException(String.format("Транзакции с id %s не найден", id));
      }
    } catch (SQLException e) {
      throw new DBException("Не считать транзакцию из базы", e);
    }
  }

  public static TransactionRepository getInstance() {
    return instance;
  }
}
