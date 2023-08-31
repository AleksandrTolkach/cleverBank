package by.toukach.cleverbank.service.impl;

import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.dto.TransactionDto;
import by.toukach.cleverbank.enumiration.TransactionType;
import by.toukach.cleverbank.exception.InsufficientFundsException;
import by.toukach.cleverbank.service.AccountService;
import by.toukach.cleverbank.service.TransactionHandler;
import by.toukach.cleverbank.service.TransactionService;

public class TransferTransactionHandlerImpl implements TransactionHandler {

  private final TransactionService transactionService = TransactionServiceImpl.getInstance();
  private final AccountService accountService = AccountServiceImpl.getInstance();

  @Override
  public TransactionDto handle(TransactionDto transactionDto) {
    AccountDto senderAccount = accountService.read(transactionDto.getSenderAccountId());
    AccountDto receiverAccount = accountService.read(transactionDto.getReceiverAccountId());

    Long value = transactionDto.getValue();

    Long senderAccountSum = senderAccount.getSum();

    if (senderAccountSum < value) {
      throw new InsufficientFundsException("На счете недостаточно средств");
    }

    senderAccountSum = senderAccountSum - value;
    Long receiverAccountSum = receiverAccount.getSum() + value;

    senderAccount.setSum(senderAccountSum);
    receiverAccount.setSum(receiverAccountSum);

    accountService.update(senderAccount);
    accountService.update(receiverAccount);

    return transactionService.create(transactionDto);
  }

  @Override
  public TransactionType type() {
    return TransactionType.TRANSFER;
  }
}
