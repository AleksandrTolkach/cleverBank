package by.toukach.cleverbank.service;

import by.toukach.cleverbank.dto.AccountDto;
import java.util.List;

public interface AccountService {

  AccountDto create(AccountDto account, Long userId);

  AccountDto read(Long id);

  List<AccountDto> readByUserId(Long userId);
}