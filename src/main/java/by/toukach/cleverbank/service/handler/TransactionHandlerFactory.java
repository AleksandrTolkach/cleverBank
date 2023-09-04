package by.toukach.cleverbank.service.handler;

import by.toukach.cleverbank.enumiration.TransactionType;

/**
 * Интерфейс представляет фабрику, ответственную за создание TransactionHandler.
 * */
public interface TransactionHandlerFactory {

  /**
   * Метод создает TransactionHandler.
   *
   * @param type тип требуемого TransactionHandler.
   * @return запрашиваемый TransactionHandler.
   */
  TransactionHandler getHandler(TransactionType type);
}
