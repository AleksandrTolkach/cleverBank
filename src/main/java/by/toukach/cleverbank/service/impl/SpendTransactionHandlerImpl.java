package by.toukach.cleverbank.service.impl;

import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.dto.TransactionDto;
import by.toukach.cleverbank.enumiration.TransactionType;
import by.toukach.cleverbank.exception.DBException;
import by.toukach.cleverbank.exception.InsufficientFundsException;
import by.toukach.cleverbank.repository.impl.DBInitializerImpl;
import by.toukach.cleverbank.service.AccountService;
import by.toukach.cleverbank.service.TransactionHandler;
import by.toukach.cleverbank.service.TransactionService;
import java.sql.Connection;
import java.sql.SQLException;

public class SpendTransactionHandlerImpl implements TransactionHandler {

  private final TransactionService transactionService = TransactionServiceImpl.getInstance();
  private final AccountService accountService = AccountServiceImpl.getInstance();

  @Override
  public TransactionDto handle(TransactionDto transactionDto) {
    Long accountId = transactionDto.getReceiverAccountId();
    synchronized (accountService) {
      AccountDto accountDto = accountService.read(accountId);
      Double sum = accountDto.getSum();
      Double value = transactionDto.getValue();
      if (sum < value) {
        throw new InsufficientFundsException("На счете недостаточно средств");
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
          throw new DBException("Не удалось отменить транзакцию");
        }
        throw new DBException("Не удалось выполнить соединение к базе");
      }
    }
    return transactionDto;
  }

  @Override
  public TransactionType type() {
    return TransactionType.SPEND;
  }
}
