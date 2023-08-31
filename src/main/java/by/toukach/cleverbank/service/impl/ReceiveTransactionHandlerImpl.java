package by.toukach.cleverbank.service.impl;

import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.dto.TransactionDto;
import by.toukach.cleverbank.enumiration.TransactionType;
import by.toukach.cleverbank.service.AccountService;
import by.toukach.cleverbank.service.TransactionHandler;
import by.toukach.cleverbank.service.TransactionService;

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
    accountService.update(accountDto);
    return transactionService.create(transactionDto);
  }

  @Override
  public TransactionType type() {
    return TransactionType.RECEIVE;
  }
}
