package by.toukach.cleverbank.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * Класс представляющий DTO для счета в банке.
 * */
@Data
@Builder
public class AccountDto {

  private Long id;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String title;
  private Long bankId;
  private Double sum;
}
