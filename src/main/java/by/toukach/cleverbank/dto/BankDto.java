package by.toukach.cleverbank.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class BankDto {

  private Long id;
  private LocalDateTime createdAt;
  private String name;
  private List<Long> customerIdList;
}
