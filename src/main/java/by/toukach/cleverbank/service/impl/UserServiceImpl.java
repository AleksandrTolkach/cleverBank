package by.toukach.cleverbank.service.impl;

import by.toukach.cleverbank.dao.User;
import by.toukach.cleverbank.dao.converter.Converter;
import by.toukach.cleverbank.dao.converter.impl.UserConverter;
import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.dto.UserDto;
import by.toukach.cleverbank.exception.UserNotFoundException;
import by.toukach.cleverbank.repository.UserRepository;
import by.toukach.cleverbank.repository.impl.UserRepositoryImpl;
import by.toukach.cleverbank.service.AccountService;
import by.toukach.cleverbank.service.UserService;
import java.util.List;

public class UserServiceImpl implements UserService {

  private static final UserService instance = new UserServiceImpl();

  private final Converter<UserDto, User> userConverter;
  private final UserRepository userRepository;
  private final AccountService accountService;

  private UserServiceImpl() {
    userConverter  = UserConverter.getInstance();
    userRepository = UserRepositoryImpl.getInstance();
    accountService = AccountServiceImpl.getInstance();
  }

  @Override
  public UserDto create(UserDto userDto) {
    User user = userRepository.create(userConverter.toEntity(userDto));
    return userConverter.toDto(user);
  }

  @Override
  public UserDto read(Long userId) {
    User user = userRepository.read(userId);
    List<Long> accountIdList = accountService.readByUserId(userId).stream()
        .map(AccountDto::getId)
        .toList();
    UserDto userDto = userConverter.toDto(user);
    userDto.setAccountIdList(accountIdList);
    return userDto;
  }

  @Override
  public UserDto readByLogin(String login) {
    User user = userRepository.readByLogin(login);
    UserDto userDto = userConverter.toDto(user);
    List<Long> accountIdList = accountService.readByUserId(user.getId()).stream()
        .map(AccountDto::getId)
        .toList();
    userDto.setAccountIdList(accountIdList);
    return userDto;
  }

  public static UserService getInstance() {
    return instance;
  }
}
