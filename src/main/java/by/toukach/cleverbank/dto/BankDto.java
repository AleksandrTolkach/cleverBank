package by.toukach.cleverbank.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankDto {

  private Long id;
  private LocalDateTime createdAt;
  private String name;
}
