package by.toukach.cleverbank.dto;

import by.toukach.cleverbank.enumiration.TransactionType;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * Класс представляющий DTO для операции со счетом.
 * */
@Data
@Builder
public class TransactionDto {

  private Long id;
  private LocalDateTime date;
  private TransactionType type;
  private Long senderBankId;
  private Long receiverBankId;
  private Long senderAccountId;
  private Long receiverAccountId;
  private Double value;
}
