package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.LogInDto;
import by.toukach.cleverbank.dto.LogInDto.LogInDtoBuilder;
import by.toukach.cleverbank.service.AuthService;
import by.toukach.cleverbank.service.impl.AuthServiceImpl;
import java.util.Scanner;

public class LogInViewChain extends ViewChain {

  private final AuthService authService;

  public LogInViewChain() {
    authService = AuthServiceImpl.getInstance();
  }

  @Override
  public void handle() {
    Scanner scanner = getScanner();

    LogInDtoBuilder logInDtoBuilder = LogInDto.builder();

    System.out.println("Введите логин");
    logInDtoBuilder.login(scanner.nextLine());
    System.out.println("Введите пароль");
    logInDtoBuilder.password(scanner.nextLine());

    setUserDto(authService.logIn(logInDtoBuilder.build()));
    System.out.println(getUserDto());

    setNextView(new AccountActionViewChain(getUserDto()));
  }
}
