package by.toukach.cleverbank.service.handler;

import by.toukach.cleverbank.enumiration.TransactionType;
import by.toukach.cleverbank.dto.TransactionDto;

/**
 * Интерфейс представляющий обработчик транзакций.
 * */
public interface TransactionHandler {

  /**
   * Метод для обработки транзакции.
   *
   * @param transactionDto транзакция для обработки.
   * @return обработанная транзакция.
   */
  TransactionDto handle(TransactionDto transactionDto);

  /**
   * Метод для получения типа обработчика.
   *
   * @return тип обработчика.
   */
  TransactionType type();
}
