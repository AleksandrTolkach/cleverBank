package by.toukach.cleverbank.dao.mapper.impl;

import by.toukach.cleverbank.dao.Transaction;
import by.toukach.cleverbank.enumiration.TransactionType;
import by.toukach.cleverbank.dao.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionMapper implements RowMapper<Transaction> {

  private static final RowMapper<Transaction> instance = new TransactionMapper();

  @Override
  public Transaction mapRow(ResultSet resultSet) throws SQLException {
    return Transaction.builder()
        .id(resultSet.getLong("id"))
        .date(resultSet.getObject("date", LocalDateTime.class))
        .type(resultSet.getObject("type", TransactionType.class))
        .senderBankId(resultSet.getLong("sender_bank_id"))
        .receiverBankId(resultSet.getLong("receiver_bank_id"))
        .senderAccountId(resultSet.getLong("sender_account_id"))
        .receiverAccountId(resultSet.getLong("receiver_account_id"))
        .value(resultSet.getLong("value"))
        .build();
  }

  public static RowMapper<Transaction> getInstance() {
    return instance;
  }
}
