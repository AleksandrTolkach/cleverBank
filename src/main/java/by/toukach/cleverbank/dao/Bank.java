package by.toukach.cleverbank.dao;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class Bank {

  private Long id;
  private LocalDateTime createdAt;
  private String name;
  private List<Long> customerIdList;
}
