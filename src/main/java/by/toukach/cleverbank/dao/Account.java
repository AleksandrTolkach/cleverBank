package by.toukach.cleverbank.dao;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * Класс представляющий DAO для счета в банке.
 * */
@Data
@Builder
public class Account {

  private Long id;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String title;
  private Long bankId;
  private Double sum;
}
