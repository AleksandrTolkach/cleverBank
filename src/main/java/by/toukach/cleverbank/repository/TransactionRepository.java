package by.toukach.cleverbank.repository;

import by.toukach.cleverbank.dao.Transaction;
import java.sql.Connection;

public interface TransactionRepository {

  Transaction create(Transaction transaction, Connection connection);

  Transaction read(Long id);
}
