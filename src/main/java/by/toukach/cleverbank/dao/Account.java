package by.toukach.cleverbank.dao;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Account {

  private Long id;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String title;
  private String currency;
}
