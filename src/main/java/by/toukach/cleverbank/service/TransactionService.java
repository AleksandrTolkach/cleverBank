package by.toukach.cleverbank.service;

import by.toukach.cleverbank.dto.TransactionDto;
import java.sql.Connection;

/**
 * Интерфейс для выполнения операций с Transaction.
 * */
public interface TransactionService {

  /**
   * Метод для создания операции.
   *
   * @param transactionDto создаваемая операция.
   * @param connection соединение к базе.
   * @return созданная операция.
   */
  TransactionDto create(TransactionDto transactionDto, Connection connection);

  /**
   * Метод для чтения операции.
   *
   * @param id id запрашиваемой операции.
   * @return запрашиваемая операция.
   */
  TransactionDto read(Long id);
}
