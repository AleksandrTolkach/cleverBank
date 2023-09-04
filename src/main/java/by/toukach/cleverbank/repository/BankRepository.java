package by.toukach.cleverbank.repository;

import by.toukach.cleverbank.dao.Bank;

/**
 * Интерфейс для выполнения запросов, связанных с банков, в базу.
 * */
public interface BankRepository {

  /**
   * Метод для чтения банка из базы.
   *
   * @param id id запрашиваемого банка.
   * @return запрашиваемый банк.
   */
  Bank read(Long id);
}
