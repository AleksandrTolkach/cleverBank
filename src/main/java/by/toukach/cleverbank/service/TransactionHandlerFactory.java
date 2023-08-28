package by.toukach.cleverbank.service;

import by.toukach.cleverbank.enumiration.TransactionType;

public interface TransactionHandlerFactory {

  TransactionHandler getHandler(TransactionType type);
}
