package by.toukach.cleverbank.repository;

import by.toukach.cleverbank.dao.Account;
import java.sql.Connection;
import java.util.List;

public interface AccountRepository {

  Account create(Account account, Long userId);

  Account read(Long id);

  List<Account> readByUserId(Long userId);

  List<Account> readAll();

  Account update(Account account, Connection connection);
}
