package by.toukach.cleverbank.service;

import by.toukach.cleverbank.dto.TransactionDto;

public interface CheckService {

  void print(TransactionDto transaction);
}
