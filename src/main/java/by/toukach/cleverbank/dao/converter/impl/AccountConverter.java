package by.toukach.cleverbank.dao.converter.impl;

import by.toukach.cleverbank.dao.Account;
import by.toukach.cleverbank.dao.converter.Converter;
import by.toukach.cleverbank.dto.AccountDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Класс для конвертации Account из DTO в DAO и обратно.
 * */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountConverter implements Converter<AccountDto, Account> {

  private static final Converter<AccountDto, Account> instance = new AccountConverter();

  @Override
  public AccountDto toDto(Account dao) {
    return AccountDto.builder()
        .id(dao.getId())
        .createdAt(dao.getCreatedAt())
        .updatedAt(dao.getUpdatedAt())
        .title(dao.getTitle())
        .bankId(dao.getBankId())
        .sum(dao.getSum())
        .build();
  }

  @Override
  public Account toEntity(AccountDto dto) {
    return Account.builder()
        .id(dto.getId())
        .createdAt(dto.getCreatedAt())
        .updatedAt(dto.getUpdatedAt())
        .title(dto.getTitle())
        .bankId(dto.getBankId())
        .sum(dto.getSum())
        .build();
  }

  public static Converter<AccountDto, Account> getInstance() {
    return instance;
  }
}
