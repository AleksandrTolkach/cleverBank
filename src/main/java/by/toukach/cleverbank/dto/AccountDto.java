package by.toukach.cleverbank.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AccountDto {

  private Long id;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String title;
  private String currency;
}
