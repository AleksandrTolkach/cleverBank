package by.toukach.cleverbank.service;

import by.toukach.cleverbank.dto.TransactionDto;

public interface TransactionService {

  TransactionDto create(TransactionDto transactionDto);

  TransactionDto read(Long id);
}
