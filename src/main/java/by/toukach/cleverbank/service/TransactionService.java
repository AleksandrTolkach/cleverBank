package by.toukach.cleverbank.service;

import by.toukach.cleverbank.dto.TransactionDto;
import java.sql.Connection;

public interface TransactionService {

  TransactionDto create(TransactionDto transactionDto, Connection connection);

  TransactionDto read(Long id);
}
