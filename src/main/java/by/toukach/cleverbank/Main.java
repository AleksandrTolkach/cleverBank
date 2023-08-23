package by.toukach.cleverbank;

import by.toukach.cleverbank.dto.LogInDto;
import by.toukach.cleverbank.dto.LogInDto.LogInDtoBuilder;
import by.toukach.cleverbank.dto.SignUpDto;
import by.toukach.cleverbank.dto.SignUpDto.SignUpDtoBuilder;
import by.toukach.cleverbank.dto.UserDto;
import by.toukach.cleverbank.service.AuthService;
import by.toukach.cleverbank.service.UserService;
import by.toukach.cleverbank.service.impl.AuthServiceImpl;
import by.toukach.cleverbank.service.impl.UserServiceImpl;
import java.util.Scanner;

public class Main {

  private static final UserService userService = UserServiceImpl.getInstance();
  private static final AuthService authService = AuthServiceImpl.getInstance();

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Приветствуем!\nВыберите действие:\n1.Войти\n2.Зарегистрироваться");

    int answer = scanner.nextInt();
    scanner.nextLine();

    if (answer == 1) {
      LogInDtoBuilder logInDtoBuilder = LogInDto.builder();

      System.out.println("Введите логин");
      logInDtoBuilder.login(scanner.nextLine());
      System.out.println("Введите пароль");
      logInDtoBuilder.password(scanner.nextLine());

      authService.logIn(logInDtoBuilder.build());
    } else if (answer == 2){
      SignUpDtoBuilder signInDtoBuilder = SignUpDto.builder();

      System.out.println("Введите логин");
      signInDtoBuilder.login(scanner.nextLine());
      System.out.println("Введите пароль");
      signInDtoBuilder.password(scanner.nextLine());
      System.out.println("Введите имя");
      signInDtoBuilder.firstname(scanner.nextLine());
      System.out.println("Введите фамилию");
      signInDtoBuilder.lastname(scanner.nextLine());

      UserDto userDto = authService.signUp(signInDtoBuilder.build());
      System.out.println(userDto);
    } else {
      System.out.println("id = 5");
      System.out.println(userService.read(5L));
      System.out.println("login = z");
      System.out.println(userService.readByLogin("z"));
    }
  }
}
