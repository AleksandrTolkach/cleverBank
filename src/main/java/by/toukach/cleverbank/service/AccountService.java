package by.toukach.cleverbank.service;

import by.toukach.cleverbank.dto.AccountDto;
import java.sql.Connection;
import java.util.List;

/**
 * Интерфейс для выполнения операций со счетом.
 * */
public interface AccountService {

  /**
   * Метод для создания счета.
   *
   * @param account создаваемый счет.
   * @param userId id владельца счета.
   * @return созданный счет.
   */
  AccountDto create(AccountDto account, Long userId);

  /**
   * Метод для чтения счета из базы.
   *
   * @param id id запрашиваемого счета.
   * @return запрашиваемый счет.
   */
  AccountDto read(Long id);

  /**
   * Метод для чтения счетов из базы по id владельца.
   *
   * @param userId id владельца счета.
   * @return список запрашиваемых счетов.
   */
  List<AccountDto> readByUserId(Long userId);

  /**
   * Метод для чтения счетов из базы.
   *
   * @return список счетов.
   */
  List<AccountDto> readAll();

  /**
   * Метод для обновления счета в базе.
   *
   * @param accountDto обновляемый счет.
   * @param connection соединение к базе.
   * @return обновленный счет.
   */
  AccountDto update(AccountDto accountDto, Connection connection);
}
