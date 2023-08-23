package by.toukach.cleverbank.service;

import by.toukach.cleverbank.dto.UserDto;

public interface UserService {

  UserDto create(UserDto userDto);

  UserDto read(Long userId);

  UserDto readByLogin(String login);

}
