package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.service.AccountService;
import by.toukach.cleverbank.service.impl.AccountServiceImpl;

/**
 * Класс для вывода формы с данными о счете в консоль.
 * */
public abstract class AccountViewChain extends ViewChain {

  private final AccountService accountService = AccountServiceImpl.getInstance();
  private AccountDto accountDto;

  public AccountService getAccountService() {
    return accountService;
  }

  public AccountDto getAccountDto() {
    return accountDto;
  }

  public void setAccountDto(AccountDto accountDto) {
    this.accountDto = accountDto;
  }
}
