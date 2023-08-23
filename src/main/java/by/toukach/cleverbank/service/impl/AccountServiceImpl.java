package by.toukach.cleverbank.service.impl;

import by.toukach.cleverbank.dao.Account;
import by.toukach.cleverbank.dao.converter.Converter;
import by.toukach.cleverbank.dao.converter.impl.AccountConverter;
import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.repository.AccountRepository;
import by.toukach.cleverbank.repository.impl.AccountRepositoryImpl;
import by.toukach.cleverbank.service.AccountService;
import java.util.List;

public class AccountServiceImpl implements AccountService {

  private static final AccountService instance = new AccountServiceImpl();

  private final AccountRepository accountRepository;
  private final Converter<AccountDto, Account> accountConverter;

  public AccountServiceImpl() {
    accountRepository = AccountRepositoryImpl.getInstance();
    accountConverter = AccountConverter.getInstance();
  }

  @Override
  public AccountDto create(AccountDto accountDto, Long userId) {
    Account account = accountRepository.create(accountConverter.toEntity(accountDto), userId);
    return accountConverter.toDto(account);
  }

  @Override
  public AccountDto read(Long id) {
    return accountConverter.toDto(accountRepository.read(id));
  }

  @Override
  public List<AccountDto> readByUserId(Long userId) {
    return accountRepository.readByUserId(userId).stream()
        .map(accountConverter::toDto)
        .toList();
  }

  public static AccountService getInstance() {
    return instance;
  }
}
