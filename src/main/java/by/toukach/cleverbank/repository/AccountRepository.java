package by.toukach.cleverbank.repository;

import by.toukach.cleverbank.dao.Account;
import java.util.List;

public interface AccountRepository {

  Account create(Account account, Long userId);

  Account read(Long id);

  List<Account> readByUserId(Long userId);

  Account update(Account account);
}
