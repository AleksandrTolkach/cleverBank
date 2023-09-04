package by.toukach.cleverbank.dao;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * Класс представляющий DAO для банка.
 * */
@Data
@Builder
public class Bank {

  private Long id;
  private LocalDateTime createdAt;
  private String name;
}
