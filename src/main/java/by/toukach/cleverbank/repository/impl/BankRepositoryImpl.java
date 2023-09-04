package by.toukach.cleverbank.repository.impl;

import by.toukach.cleverbank.dao.Bank;
import by.toukach.cleverbank.dao.mapper.RowMapper;
import by.toukach.cleverbank.dao.mapper.impl.BankMapper;
import by.toukach.cleverbank.exception.DBException;
import by.toukach.cleverbank.exception.EntityNotFoundException;
import by.toukach.cleverbank.exception.ExceptionMessage;
import by.toukach.cleverbank.repository.BankRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * Класс для выполнения запросов, связанных с банков, в базу.
 * */
public class BankRepositoryImpl implements BankRepository {

  private static final BankRepository instance = new BankRepositoryImpl();

  private final DataSource dataSource;
  private final RowMapper<Bank> bankRowMapper;

  private BankRepositoryImpl() {
    dataSource = DBInitializerImpl.getInstance().getDataSource();
    bankRowMapper = BankMapper.getInstance();
  }

  @Override
  public Bank read(Long id) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(
            "SELECT id, created_at, name FROM application.bank "
                + "WHERE id = ?"
        )) {
      statement.setLong(1, id);

      ResultSet resultSet = statement.executeQuery();

      if (!resultSet.wasNull() && resultSet.next()) {
        return bankRowMapper.mapRow(resultSet);
      } else {
        throw new EntityNotFoundException(
            String.format(ExceptionMessage.BANK_NOT_FOUND_MESSAGE, id));
      }

    } catch (SQLException e) {
      throw new DBException(ExceptionMessage.DB_READ_MESSAGE, e);
    }
  }

  public static BankRepository getInstance() {
    return instance;
  }
}
