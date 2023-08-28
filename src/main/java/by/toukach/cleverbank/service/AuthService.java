package by.toukach.cleverbank.service;

import by.toukach.cleverbank.dto.LogInDto;
import by.toukach.cleverbank.dto.SignUpDto;
import by.toukach.cleverbank.dto.UserDto;

public interface AuthService {

  UserDto logIn(LogInDto logInDto);

  UserDto signUp(SignUpDto signUpDto);
}
