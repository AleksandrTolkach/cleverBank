package by.toukach.cleverbank.repository;

import by.toukach.cleverbank.dao.Transaction;
import java.sql.Connection;

/**
 * Интерфейс для выполнения запросов, связанных с операциями, в базу.
 * */
public interface TransactionRepository {

  /**
   * Метод для создания операции в базе.
   *
   * @param transaction операция для создания.
   * @param connection соединение к базе.
   * @return созданная транзакция.
   */
  Transaction create(Transaction transaction, Connection connection);

  /**
   * Метод для чтения операции из базы.
   *
   * @param id id запрашиваемой операции.
   * @return запрашиваемая операция.
   */
  Transaction read(Long id);
}
