package by.toukach.cleverbank.dto;

import by.toukach.cleverbank.dao.TransactionType;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TransactionDto {

  private LocalDateTime date;
  private TransactionType type;
  private Long senderBankId;
  private Long receiverBankId;
  private Long senderAccountId;
  private Long receiverAccountId;
  private Long value;
}
