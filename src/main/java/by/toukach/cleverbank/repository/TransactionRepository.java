package by.toukach.cleverbank.repository;

import by.toukach.cleverbank.dao.Transaction;

public interface TransactionRepository {

  Transaction create(Transaction transaction);

  Transaction read(Long id);
}
