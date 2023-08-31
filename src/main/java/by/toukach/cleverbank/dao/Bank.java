package by.toukach.cleverbank.dao;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Bank {

  private Long id;
  private LocalDateTime createdAt;
  private String name;
}
