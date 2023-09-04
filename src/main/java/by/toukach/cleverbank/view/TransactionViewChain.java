package by.toukach.cleverbank.view;

import by.toukach.cleverbank.service.handler.TransactionHandlerFactory;
import by.toukach.cleverbank.service.handler.impl.TransactionHandlerFactoryImpl;

/**
 * Класс для вывода формы с данными о возможных операциях в консоль.
 * */
public abstract class TransactionViewChain extends AccountViewChain {

  private final TransactionHandlerFactory transactionHandlerFactory
      = TransactionHandlerFactoryImpl.getInstance();

  public TransactionHandlerFactory getTransactionHandlerFactory() {
    return transactionHandlerFactory;
  }
}
