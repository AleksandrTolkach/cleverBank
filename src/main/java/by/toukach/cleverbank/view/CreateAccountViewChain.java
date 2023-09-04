package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.dto.UserDto;
import java.util.Scanner;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Класс для вывода формы по созданию счета в консоль.
 * */
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CreateAccountViewChain extends AccountViewChain {

  public CreateAccountViewChain(UserDto userDto) {
    setUserDto(userDto);
  }

  @Override
  public void handle() {
    System.out.println(ViewMessage.ACCOUNT_NUMBER_MESSAGE);

    Scanner scanner = getScanner();
    String title = scanner.nextLine();

    AccountDto accountDto = AccountDto.builder()
        .title(title)
        .sum(0.0)
        .build();

    accountDto = getAccountService().create(accountDto, getUserDto().getId());

    System.out.println(accountDto);

    setNextView(new SpecificAccountViewChain(accountDto, getUserDto()));
  }
}
