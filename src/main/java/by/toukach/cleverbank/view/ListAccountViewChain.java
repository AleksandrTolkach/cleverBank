package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.dto.UserDto;
import java.util.List;
import java.util.Scanner;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ListAccountViewChain extends AccountViewChain {

  public ListAccountViewChain(UserDto userDto) {
    setUserDto(userDto);
  }

  @Override
  public void handle() {
    List<AccountDto> accountDtos = getAccountService().readByUserId(getUserDto().getId());
    if (accountDtos.isEmpty()) {
      System.out.println("У вас нет счетов");
      setNextView(new AccountActionViewChain(getUserDto()));
      return;
    }

    System.out.println("Выберите счет:");

    accountDtos.forEach(a -> System.out.println(String.format("%s. %s", a.getId(), a.getTitle())));

    Scanner scanner = getScanner();
    Long answer = scanner.nextLong();
    scanner.nextLine();

    AccountDto accountDto = accountDtos.stream()
        .filter(a -> a.getId().equals(answer))
        .findFirst()
        .orElse(null);

    if (accountDto == null) {
      System.out.println("Выбран неверный вариант");
      setNextView(this);
    } else {
      setNextView(new SpecificAccountViewChain(accountDto, getUserDto()));
    }
  }
}
