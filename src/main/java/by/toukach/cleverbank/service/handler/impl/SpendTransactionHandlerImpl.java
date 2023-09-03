package by.toukach.cleverbank.service.handler.impl;

import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.dto.TransactionDto;
import by.toukach.cleverbank.enumiration.TransactionType;
import by.toukach.cleverbank.exception.ArgumentValueException;
import by.toukach.cleverbank.exception.DBException;
import by.toukach.cleverbank.exception.ExceptionMessage;
import by.toukach.cleverbank.exception.InsufficientFundsException;
import by.toukach.cleverbank.repository.impl.DBInitializerImpl;
import by.toukach.cleverbank.service.AccountService;
import by.toukach.cleverbank.service.handler.TransactionHandler;
import by.toukach.cleverbank.service.TransactionService;
import by.toukach.cleverbank.service.impl.AccountServiceImpl;
import by.toukach.cleverbank.service.impl.TransactionServiceImpl;
import java.sql.Connection;
import java.sql.SQLException;

public class SpendTransactionHandlerImpl implements TransactionHandler {

  private final TransactionService transactionService = TransactionServiceImpl.getInstance();
  private final AccountService accountService = AccountServiceImpl.getInstance();

  @Override
  public TransactionDto handle(TransactionDto transactionDto) {
    Long accountId = transactionDto.getReceiverAccountId();
    Double value = transactionDto.getValue();

    if (value <= 0) {
      throw new ArgumentValueException(ExceptionMessage.POSITIVE_ARGUMENT_VALUE_MESSAGE);
    }

    synchronized (accountService) {
      AccountDto accountDto = accountService.read(accountId);
      Double sum = accountDto.getSum();

      if (sum < value) {
        throw new InsufficientFundsException(ExceptionMessage.INSUFFICIENT_FUNDS_MESSAGE);
      }

      sum -= value;
      accountDto.setSum(sum);

      Connection connection = null;
      try {
        connection = DBInitializerImpl.getInstance().getDataSource().getConnection();
        connection.setAutoCommit(false);

        accountService.update(accountDto, connection);
        transactionDto = transactionService.create(transactionDto, connection);

        connection.commit();

      } catch (SQLException e) {
        try {

          connection.rollback();

        } catch (SQLException ex) {
          throw new DBException(ExceptionMessage.TRANSACTION_ROLLBACK_MESSAGE, ex);
        }
        throw new DBException(ExceptionMessage.DB_CONNECT_MESSAGE, e);
      }
    }
    return transactionDto;
  }

  @Override
  public TransactionType type() {
    return TransactionType.SPEND;
  }
}
