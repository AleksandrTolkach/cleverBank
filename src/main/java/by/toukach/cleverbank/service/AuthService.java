package by.toukach.cleverbank.service;

import by.toukach.cleverbank.dto.LogInDto;
import by.toukach.cleverbank.dto.SignUpDto;
import by.toukach.cleverbank.dto.UserDto;

/**
 * Интерфейс для аутентификации пользователей.
 * */
public interface AuthService {

  /**
   * Метод для входа в приложение.
   *
   * @param logInDto данные пользователя.
   * @return вошедший пользователь.
   */
  UserDto logIn(LogInDto logInDto);

  /**
   * Метод для регистрации пользователя в приложении.
   *
   * @param signUpDto данные пользователя.
   * @return зарегистрированный пользователь.
   */
  UserDto signUp(SignUpDto signUpDto);
}
