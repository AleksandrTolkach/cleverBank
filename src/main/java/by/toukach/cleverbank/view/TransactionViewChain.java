package by.toukach.cleverbank.view;

import by.toukach.cleverbank.service.handler.TransactionHandlerFactory;
import by.toukach.cleverbank.service.TransactionService;
import by.toukach.cleverbank.service.handler.impl.TransactionHandlerFactoryImpl;
import by.toukach.cleverbank.service.impl.TransactionServiceImpl;

public abstract class TransactionViewChain extends AccountViewChain {

  private final TransactionHandlerFactory transactionHandlerFactory
      = TransactionHandlerFactoryImpl.getInstance();
  private final TransactionService transactionService = TransactionServiceImpl.getInstance();

  public TransactionHandlerFactory getTransactionHandlerFactory() {
    return transactionHandlerFactory;
  }
}
