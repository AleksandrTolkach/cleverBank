package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.dto.UserDto;
import java.util.List;
import java.util.Scanner;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Класс для вывода формы со списком счетов в консоль.
 * */
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ListAccountViewChain extends AccountViewChain {

  public ListAccountViewChain(UserDto userDto) {
    setUserDto(userDto);
  }

  @Override
  public void handle() {
    List<AccountDto> accountDtos = getAccountService().readByUserId(getUserDto().getId());
    if (accountDtos.isEmpty()) {
      System.out.println(ViewMessage.ACCOUNTS_NOT_EXIST_MESSAGE);
      setNextView(new AccountActionViewChain(getUserDto()));
      return;
    }

    System.out.println(ViewMessage.ACCOUNT_CHOOSE_MESSAGE);

    accountDtos.forEach(a -> System.out.println(String.format("%s. %s", a.getId(), a.getTitle())));

    Scanner scanner = getScanner();
    Long answer = scanner.nextLong();
    scanner.nextLine();

    AccountDto accountDto = accountDtos.stream()
        .filter(a -> a.getId().equals(answer))
        .findFirst()
        .orElse(null);

    if (accountDto == null) {
      setNextView(new UnknownViewChain(this));
    } else {
      setNextView(new SpecificAccountViewChain(accountDto, getUserDto()));
    }
  }
}
