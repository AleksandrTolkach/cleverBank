package by.toukach.cleverbank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LogInDto {

  private String login;
  private String password;
}
