package by.toukach.cleverbank.service.impl;

import by.toukach.cleverbank.enumiration.TransactionType;
import by.toukach.cleverbank.service.TransactionHandler;
import by.toukach.cleverbank.service.TransactionHandlerFactory;
import java.util.HashMap;
import java.util.Map;

public class TransactionHandlerFactoryImpl implements TransactionHandlerFactory {

  private static final TransactionHandlerFactory instance = new TransactionHandlerFactoryImpl();

  private final Map<TransactionType, TransactionHandler> handlerMap = new HashMap<>();

  private TransactionHandlerFactoryImpl() {
    TransactionHandler sendTransactionHandler = new SpendTransactionHandlerImpl();
    TransactionHandler receiveTransactionHandler = new ReceiveTransactionHandlerImpl();
    TransactionHandler transferTransactionHandler = new TransferTransactionHandlerImpl();

    handlerMap.put(sendTransactionHandler.type(), sendTransactionHandler);
    handlerMap.put(receiveTransactionHandler.type(), receiveTransactionHandler);
    handlerMap.put(transferTransactionHandler.type(), transferTransactionHandler);
  }

  @Override
  public TransactionHandler getHandler(TransactionType type) {
    return handlerMap.get(type);
  }

  public static TransactionHandlerFactory getInstance() {
    return instance;
  }
}
