package by.toukach.cleverbank.repository;

import by.toukach.cleverbank.dao.Bank;

public interface BankRepository {

  Bank read(Long id);
}
