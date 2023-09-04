package by.toukach.cleverbank.service;

import by.toukach.cleverbank.dto.TransactionDto;

/**
 * Интерфейс для выполнения операций с чеком.
 * */
public interface CheckService {

  /**
   * Метод для печати чека.
   *
   * @param transaction
   */
  void print(TransactionDto transaction);
}
