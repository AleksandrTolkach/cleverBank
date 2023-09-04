package by.toukach.cleverbank.service;

import by.toukach.cleverbank.dto.BankDto;

/**
 * Интерфейс для выполнения операций с банками.
 * */
public interface BankService {

  /**
   * Метод для чтения банка из базы.
   *
   * @param id id запрашиваемого банка.
   * @return запрашиваемый банк.
   */
  BankDto read(Long id);
}
