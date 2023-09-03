package by.toukach.cleverbank.service.impl;

import by.toukach.cleverbank.dao.Transaction;
import by.toukach.cleverbank.dao.converter.Converter;
import by.toukach.cleverbank.dao.converter.impl.TransactionConverter;
import by.toukach.cleverbank.dto.TransactionDto;
import by.toukach.cleverbank.repository.TransactionRepository;
import by.toukach.cleverbank.repository.impl.TransactionRepositoryImpl;
import by.toukach.cleverbank.service.CheckService;
import by.toukach.cleverbank.service.TransactionService;
import java.sql.Connection;
import java.time.LocalDateTime;

public class TransactionServiceImpl implements TransactionService {

  private static final TransactionService instance = new TransactionServiceImpl();

  private final TransactionRepository transactionRepository;
  private final Converter<TransactionDto, Transaction> transactionConverter;
  private final CheckService checkService;

  private TransactionServiceImpl() {
    transactionRepository = TransactionRepositoryImpl.getInstance();
    transactionConverter = TransactionConverter.getInstance();
    checkService = CheckServiceImpl.getInstance();
  }

  @Override
  public TransactionDto create(TransactionDto transactionDto, Connection connection) {
    transactionDto.setDate(LocalDateTime.now());
    Transaction transaction = transactionConverter.toEntity(transactionDto);
    transaction = transactionRepository.create(transaction, connection);
    transactionDto = transactionConverter.toDto(transaction);
    checkService.print(transactionDto);
    return transactionDto;
  }

  @Override
  public TransactionDto read(Long id) {
    return transactionConverter.toDto(transactionRepository.read(id));
  }

  public static TransactionService getInstance() {
    return instance;
  }
}
