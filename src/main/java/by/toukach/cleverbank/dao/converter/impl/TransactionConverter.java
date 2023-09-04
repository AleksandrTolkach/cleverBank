package by.toukach.cleverbank.dao.converter.impl;

import by.toukach.cleverbank.dao.Transaction;
import by.toukach.cleverbank.dao.converter.Converter;
import by.toukach.cleverbank.dto.TransactionDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Класс для конвертации Transaction из DTO в DAO и обратно.
 * */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionConverter implements Converter<TransactionDto, Transaction> {

  private static final Converter<TransactionDto, Transaction> instance = new TransactionConverter();

  @Override
  public TransactionDto toDto(Transaction dao) {
    return TransactionDto.builder()
        .id(dao.getId())
        .date(dao.getDate())
        .type(dao.getType())
        .senderBankId(dao.getSenderBankId())
        .receiverBankId(dao.getReceiverBankId())
        .senderAccountId(dao.getSenderAccountId())
        .receiverAccountId(dao.getReceiverAccountId())
        .value(dao.getValue())
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
