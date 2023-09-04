package by.toukach.cleverbank.dao.converter.impl;

import by.toukach.cleverbank.dao.User;
import by.toukach.cleverbank.dao.converter.Converter;
import by.toukach.cleverbank.dto.UserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Класс для конвертации User из DTO в DAO и обратно.
 * */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter implements Converter<UserDto, User> {

  private static final Converter<UserDto, User> instance = new UserConverter();

  @Override
  public UserDto toDto(User dao) {
    return UserDto.builder()
        .id(dao.getId())
        .createdAt(dao.getCreatedAt())
        .login(dao.getLogin())
        .password(dao.getPassword())
        .firstname(dao.getFirstname())
        .lastname(dao.getLastname())
        .build();
  }

  @Override
  public User toEntity(UserDto dto) {
    return User.builder()
        .id(dto.getId())
        .createdAt(dto.getCreatedAt())
        .login(dto.getLogin())
        .password(dto.getPassword())
        .firstname(dto.getFirstname())
        .lastname(dto.getLastname())
        .build();
  }

  public static Converter<UserDto, User> getInstance() {
    return instance;
  }
}
