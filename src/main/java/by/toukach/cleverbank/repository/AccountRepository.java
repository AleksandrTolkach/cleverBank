package by.toukach.cleverbank.repository;

import by.toukach.cleverbank.dao.Account;
import java.sql.Connection;
import java.util.List;

/**
 * Интерфейс для выполнения запросов, связанных со счетом, в базу.
 * */
public interface AccountRepository {

  /** Метод для создания счета в базе.
   *
   * @param account создаваемый счет.
   * @param userId id владельца счета.
   * @return созданный счет.
   */
  Account create(Account account, Long userId);

  /**
   * Метод для чтения счета из базы.
   *
   * @param id id счета.
   * @return запрашиваемый счет.
   */
  Account read(Long id);

  /**
   * Метод для чтения счетов определенного пользователя.
   *
   * @param userId id пользователя.
   * @return список счетов.
   */
  List<Account> readByUserId(Long userId);

  /**
   * Метод для чтения всех счетов из базы.
   *
   * @return запрашиваемые счета.
   */
  List<Account> readAll();

  /**
   * Метод для обновления счета в базе.
   *
   * @param account обновляемый счет.
   * @param connection соединение к базе.
   * @return обновленный счет.
   */
  Account update(Account account, Connection connection);
}
