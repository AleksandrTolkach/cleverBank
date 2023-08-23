package by.toukach.cleverbank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpDto {

  private String login;
  private String password;
  private String firstname;
  private String lastname;
}
