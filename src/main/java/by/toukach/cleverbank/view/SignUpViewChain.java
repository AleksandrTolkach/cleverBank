package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.SignUpDto;
import by.toukach.cleverbank.dto.SignUpDto.SignUpDtoBuilder;
import by.toukach.cleverbank.exception.UserDuplicateException;
import by.toukach.cleverbank.service.AuthService;
import by.toukach.cleverbank.service.impl.AuthServiceImpl;
import java.util.Scanner;

/**
 * Класс для вывода формы регистрации в консоль.
 * */
public class SignUpViewChain extends ViewChain {

  private final AuthService authService;

  public SignUpViewChain() {
    authService = AuthServiceImpl.getInstance();
  }

  @Override
  public void handle() {
    Scanner scanner = getScanner();
    SignUpDtoBuilder signUpDtoBuilder = SignUpDto.builder();

    System.out.println(ViewMessage.LOGIN_MESSAGE);
    signUpDtoBuilder.login(scanner.nextLine());
    System.out.println(ViewMessage.PASSWORD_MESSAGE);
    signUpDtoBuilder.password(scanner.nextLine());
    System.out.println(ViewMessage.NAME_MESSAGE);
    signUpDtoBuilder.firstname(scanner.nextLine());
    System.out.println(ViewMessage.SURNAME_MESSAGE);
    signUpDtoBuilder.lastname(scanner.nextLine());

    try {
      setUserDto(authService.signUp(signUpDtoBuilder.build()));
      setNextView(new AccountActionViewChain(getUserDto()));
    } catch (UserDuplicateException e) {
      System.out.println(e.getMessage());
      setNextView(new EntryViewChain());
    }
  }
}
