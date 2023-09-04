package by.toukach.cleverbank.service.handler.impl;

import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.dto.TransactionDto;
import by.toukach.cleverbank.enumiration.TransactionType;
import by.toukach.cleverbank.exception.ArgumentValueException;
import by.toukach.cleverbank.exception.DBException;
import by.toukach.cleverbank.exception.ExceptionMessage;
import by.toukach.cleverbank.exception.InsufficientFundsException;
import by.toukach.cleverbank.exception.TransferException;
import by.toukach.cleverbank.repository.impl.DBInitializerImpl;
import by.toukach.cleverbank.service.AccountService;
import by.toukach.cleverbank.service.handler.TransactionHandler;
import by.toukach.cleverbank.service.TransactionService;
import by.toukach.cleverbank.service.impl.AccountServiceImpl;
import by.toukach.cleverbank.service.impl.TransactionServiceImpl;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Класс представляющий обработчик транзакций по переводу средств.
 * */
public class TransferTransactionHandlerImpl implements TransactionHandler {

  private final TransactionService transactionService = TransactionServiceImpl.getInstance();
  private final AccountService accountService = AccountServiceImpl.getInstance();

  @Override
  public TransactionDto handle(TransactionDto transactionDto) {
    Double value = transactionDto.getValue();
    if (value <= 0) {
      throw new ArgumentValueException(ExceptionMessage.POSITIVE_ARGUMENT_VALUE_MESSAGE);
    }

    synchronized (accountService) {
      AccountDto senderAccount = accountService.read(transactionDto.getSenderAccountId());
      AccountDto receiverAccount = accountService.read(transactionDto.getReceiverAccountId());

      if (senderAccount.getId().equals(receiverAccount.getId())) {
        throw new TransferException(ExceptionMessage.TRANSFER_LOOP_MESSAGE);
      }

      Double senderAccountSum = senderAccount.getSum();

      if (senderAccountSum < value) {
        throw new InsufficientFundsException(ExceptionMessage.INSUFFICIENT_FUNDS_MESSAGE);
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
            throw new DBException(ExceptionMessage.TRANSACTION_ROLLBACK_MESSAGE, ex);
          }
        }
        throw new DBException(ExceptionMessage.TRANSACTION_SAVE_MESSAGE, e);
      }
    }
  }

  @Override
  public TransactionType type() {
    return TransactionType.TRANSFER;
  }
}
