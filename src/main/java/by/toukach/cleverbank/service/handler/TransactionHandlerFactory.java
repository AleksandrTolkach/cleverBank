package by.toukach.cleverbank.service.handler;

import by.toukach.cleverbank.enumiration.TransactionType;

public interface TransactionHandlerFactory {

  TransactionHandler getHandler(TransactionType type);
}
