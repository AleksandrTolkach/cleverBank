package by.toukach.cleverbank.service.impl;

import by.toukach.cleverbank.dto.LogInDto;
import by.toukach.cleverbank.dto.SignUpDto;
import by.toukach.cleverbank.dto.UserDto;
import by.toukach.cleverbank.exception.EntityNotFoundException;
import by.toukach.cleverbank.exception.ExceptionMessage;
import by.toukach.cleverbank.exception.UserDuplicateException;
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
      throw new EntityNotFoundException(ExceptionMessage.WRONG_LOGIN_OR_PASSWORD_MESSAGE);
    }
  }

  @Override
  public UserDto signUp(SignUpDto signUpDto) {
    if (userService.isExists(signUpDto.getLogin())) {
      throw new UserDuplicateException(ExceptionMessage.USER_DUPLICATE_MESSAGE);
    }

    UserDto userDto = UserDto.builder()
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
