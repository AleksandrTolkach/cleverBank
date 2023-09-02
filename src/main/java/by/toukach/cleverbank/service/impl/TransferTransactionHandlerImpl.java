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

public class TransferTransactionHandlerImpl implements TransactionHandler {

  private final TransactionService transactionService = TransactionServiceImpl.getInstance();
  private final AccountService accountService = AccountServiceImpl.getInstance();

  @Override
  public TransactionDto handle(TransactionDto transactionDto) {
    synchronized (accountService) {
      AccountDto senderAccount = accountService.read(transactionDto.getSenderAccountId());
      AccountDto receiverAccount = accountService.read(transactionDto.getReceiverAccountId());

      Double value = transactionDto.getValue();

      Double senderAccountSum = senderAccount.getSum();

      if (senderAccountSum < value) {
        throw new InsufficientFundsException("На счете недостаточно средств");
      }

      senderAccountSum = senderAccountSum - value;
      Double receiverAccountSum = receiverAccount.getSum() + value;

      senderAccount.setSum(senderAccountSum);
      receiverAccount.setSum(receiverAccountSum);

      Connection connection = null;
      try {
        connection = DBInitializerImpl.getInstance().getDataSource().getConnection();
        connection.setAutoCommit(false);

        accountService.update(senderAccount, connection);
        accountService.update(receiverAccount, connection);
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
        throw new DBException("Не удалось выполнить транзакцию", e);
      }
    }
  }

  @Override
  public TransactionType type() {
    return TransactionType.TRANSFER;
  }
}
