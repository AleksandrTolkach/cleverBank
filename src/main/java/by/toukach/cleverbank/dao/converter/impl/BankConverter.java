package by.toukach.cleverbank.dao.converter.impl;

import by.toukach.cleverbank.dao.Bank;
import by.toukach.cleverbank.dao.converter.Converter;
import by.toukach.cleverbank.dto.BankDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankConverter implements Converter<BankDto, Bank> {

  private static final Converter<BankDto, Bank> instance = new BankConverter();

  @Override
  public BankDto toDto(Bank entity) {
    return BankDto.builder()
        .id(entity.getId())
        .createdAt(entity.getCreatedAt())
        .name(entity.getName())
        .build();
  }

  @Override
  public Bank toEntity(BankDto dto) {
    return Bank.builder()
        .id(dto.getId())
        .createdAt(dto.getCreatedAt())
        .name(dto.getName())
        .build();
  }

  public static Converter<BankDto, Bank> getInstance() {
    return instance;
  }
}
