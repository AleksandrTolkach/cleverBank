package by.toukach.cleverbank.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * Класс представляющий DTO для банка.
 * */
@Data
@Builder
public class BankDto {

  private Long id;
  private LocalDateTime createdAt;
  private String name;
}
