package by.toukach.cleverbank.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class UserDto {

  private Long id;
  private LocalDateTime createdAt;
  private String login;
  private String firstname;
  private String lastname;
  private List<Long> accountIdList;
}
