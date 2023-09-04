package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.LogInDto;
import by.toukach.cleverbank.dto.LogInDto.LogInDtoBuilder;
import by.toukach.cleverbank.dto.UserDto;
import by.toukach.cleverbank.exception.EntityNotFoundException;
import by.toukach.cleverbank.exception.ExceptionMessage;
import by.toukach.cleverbank.service.AuthService;
import by.toukach.cleverbank.service.impl.AuthServiceImpl;
import java.util.Scanner;

/**
 * Класс для вывода формы входа в консоль.
 * */
public class LogInViewChain extends ViewChain {

  private final AuthService authService;

  public LogInViewChain() {
    authService = AuthServiceImpl.getInstance();
  }

  @Override
  public void handle() {
    Scanner scanner = getScanner();

    LogInDtoBuilder logInDtoBuilder = LogInDto.builder();

    System.out.println(ViewMessage.LOGIN_MESSAGE);
    logInDtoBuilder.login(scanner.nextLine());
    System.out.println(ViewMessage.PASSWORD_MESSAGE);
    logInDtoBuilder.password(scanner.nextLine());

    try {
      UserDto userDto = authService.logIn(logInDtoBuilder.build());
      setUserDto(userDto);
      setNextView(new AccountActionViewChain(getUserDto()));
    } catch (EntityNotFoundException e) {
      System.out.println(ExceptionMessage.WRONG_LOGIN_OR_PASSWORD_MESSAGE);
      setNextView(new EntryViewChain());
    }
  }
}
