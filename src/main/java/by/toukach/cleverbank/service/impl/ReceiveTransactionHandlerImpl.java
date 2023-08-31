package by.toukach.cleverbank.service.impl;

import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.dto.TransactionDto;
import by.toukach.cleverbank.enumiration.TransactionType;
import by.toukach.cleverbank.exception.DBException;
import by.toukach.cleverbank.repository.impl.DBInitializerImpl;
import by.toukach.cleverbank.service.AccountService;
import by.toukach.cleverbank.service.TransactionHandler;
import by.toukach.cleverbank.service.TransactionService;
import java.sql.Connection;
import java.sql.SQLException;

public class ReceiveTransactionHandlerImpl implements TransactionHandler {

  private final TransactionService transactionService = TransactionServiceImpl.getInstance();
  private final AccountService accountService = AccountServiceImpl.getInstance();

  @Override
  public TransactionDto handle(TransactionDto transactionDto) {
    Long accountId = transactionDto.getReceiverAccountId();
    AccountDto accountDto = accountService.read(accountId);
    Long sum = accountDto.getSum();
    sum += transactionDto.getValue();
    accountDto.setSum(sum);

    Connection connection = null;
    try {
      connection = DBInitializerImpl.getInstance().getDataSource().getConnection();
      connection.setAutoCommit(false);

      accountService.update(accountDto, connection);
      transactionDto = transactionService.create(transactionDto, connection);

      connection.commit();
      return transactionDto;
    } catch (SQLException e) {
      if (connection != null) {
        try {
          connection.rollback();
        } catch (SQLException ex) {
          throw new DBException("Не удалось отменить транзакцию");
        }
      }
      throw new DBException("Не удалось выполнить подключение к базе");
    }
  }

  @Override
  public TransactionType type() {
    return TransactionType.RECEIVE;
  }
}
