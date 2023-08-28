package by.toukach.cleverbank.dao;

import by.toukach.cleverbank.enumiration.TransactionType;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {

  private Long id;
  private LocalDateTime date;
  private TransactionType type;
  private Long senderBankId;
  private Long receiverBankId;
  private Long senderAccountId;
  private Long receiverAccountId;
  private Long value;
}
