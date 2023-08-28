package by.toukach.cleverbank.service;

import by.toukach.cleverbank.enumiration.TransactionType;
import by.toukach.cleverbank.dto.TransactionDto;

public interface TransactionHandler {

  TransactionDto handle(TransactionDto transactionDto);

  TransactionType type();
}
