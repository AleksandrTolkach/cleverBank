package by.toukach.cleverbank.dao.converter.impl;

import by.toukach.cleverbank.dao.User;
import by.toukach.cleverbank.dao.converter.Converter;
import by.toukach.cleverbank.dto.UserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter implements Converter<UserDto, User> {

  private static final Converter<UserDto, User> instance = new UserConverter();

  @Override
  public UserDto toDto(User entity) {
    return UserDto.builder()
        .id(entity.getId())
        .createdAt(entity.getCreatedAt())
        .login(entity.getLogin())
        .password(entity.getPassword())
        .firstname(entity.getFirstname())
        .lastname(entity.getLastname())
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
