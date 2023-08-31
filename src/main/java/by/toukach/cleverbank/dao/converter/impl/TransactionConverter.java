package by.toukach.cleverbank.dao.converter.impl;

import by.toukach.cleverbank.dao.Transaction;
import by.toukach.cleverbank.dao.converter.Converter;
import by.toukach.cleverbank.dto.TransactionDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionConverter implements Converter<TransactionDto, Transaction> {

  private static final Converter<TransactionDto, Transaction> instance = new TransactionConverter();

  @Override
  public TransactionDto toDto(Transaction entity) {
    return TransactionDto.builder()
        .id(entity.getId())
        .date(entity.getDate())
        .type(entity.getType())
        .senderBankId(entity.getSenderBankId())
        .receiverBankId(entity.getReceiverBankId())
        .senderAccountId(entity.getSenderAccountId())
        .receiverAccountId(entity.getReceiverAccountId())
        .value(entity.getValue())
        .build();
  }

  @Override
  public Transaction toEntity(TransactionDto dto) {
    return Transaction.builder()
        .id(dto.getId())
        .date(dto.getDate())
        .type(dto.getType())
        .senderBankId(dto.getSenderBankId())
        .senderAccountId(dto.getSenderAccountId())
        .receiverBankId(dto.getReceiverBankId())
        .receiverAccountId(dto.getReceiverAccountId())
        .value(dto.getValue())
        .build();
  }

  public static Converter<TransactionDto, Transaction> getInstance() {
    return instance;
  }
}
