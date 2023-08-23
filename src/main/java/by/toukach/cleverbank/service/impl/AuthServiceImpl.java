package by.toukach.cleverbank.service.impl;

import by.toukach.cleverbank.dto.LogInDto;
import by.toukach.cleverbank.dto.SignUpDto;
import by.toukach.cleverbank.dto.UserDto;
import by.toukach.cleverbank.exception.UserDuplicateException;
import by.toukach.cleverbank.exception.UserNotFoundException;
import by.toukach.cleverbank.service.AuthService;
import by.toukach.cleverbank.service.UserService;
import java.time.LocalDateTime;

public class AuthServiceImpl implements AuthService {

  private static final AuthService instance = new AuthServiceImpl();

  private final UserService userService;

  private AuthServiceImpl() {
    userService = UserServiceImpl.getInstance();
  }

  @Override
  public UserDto logIn(LogInDto logInDto) {
    UserDto userDto = userService.readByLogin(logInDto.getLogin());
    if (userDto.getPassword().equals(logInDto.getPassword())) {
      return userDto;
    } else {
      throw new UserNotFoundException("Неверный логин или пароль");
    }
  }

  @Override
  public UserDto signUp(SignUpDto signUpDto) {
    UserDto userDto = userService.readByLogin(signUpDto.getLogin());

    if (userDto != null) {
      throw new UserDuplicateException("Пользователь с таким логином уже существует");
    }

    userDto = UserDto.builder()
        .createdAt(LocalDateTime.now())
        .login(signUpDto.getLogin())
        .password(signUpDto.getPassword())
        .firstname(signUpDto.getFirstname())
        .lastname(signUpDto.getLastname())
        .build();

    userDto = userService.create(userDto);

    return userDto;
  }

  public static AuthService getInstance() {
    return instance;
  }
}
