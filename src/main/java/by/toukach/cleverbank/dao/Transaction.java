package by.toukach.cleverbank.dao;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Transaction {

  private LocalDateTime date;
  private TransactionType type;
  private Long senderBankId;
  private Long receiverBankId;
  private Long senderAccountId;
  private Long receiverAccountId;
  private Long value;
}
