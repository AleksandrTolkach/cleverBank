package by.toukach.cleverbank.dao;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * Класс представляющий DAO для пользователя.
 * */
@Data
@Builder
public class User {

  private Long id;
  private LocalDateTime createdAt;
  private String login;
  private String password;
  private String firstname;
  private String lastname;
  private List<Long> accountIdList;
}
