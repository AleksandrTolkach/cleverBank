package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.SignUpDto;
import by.toukach.cleverbank.dto.SignUpDto.SignUpDtoBuilder;
import by.toukach.cleverbank.service.AuthService;
import by.toukach.cleverbank.service.impl.AuthServiceImpl;
import java.util.Scanner;

public class SignUpViewChain extends ViewChain {

  private final AuthService authService;

  public SignUpViewChain() {
    authService = AuthServiceImpl.getInstance();
  }

  @Override
  public void handle() {
    Scanner scanner = getScanner();
    SignUpDtoBuilder signUpDtoBuilder = SignUpDto.builder();

    System.out.println("Введите логин");
    signUpDtoBuilder.login(scanner.nextLine());
    System.out.println("Введите пароль");
    signUpDtoBuilder.password(scanner.nextLine());
    System.out.println("Введите имя");
    signUpDtoBuilder.firstname(scanner.nextLine());
    System.out.println("Введите фамилию");
    signUpDtoBuilder.lastname(scanner.nextLine());

    setUserDto(authService.signUp(signUpDtoBuilder.build()));
    System.out.println(getUserDto());

    setNextView(new AccountActionViewChain(getUserDto()));
  }
}
