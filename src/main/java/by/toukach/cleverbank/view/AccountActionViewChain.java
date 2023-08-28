package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.UserDto;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountActionViewChain extends ViewChain {

  private Map<Integer, ViewChain> viewChainMap = new HashMap<>();

  public AccountActionViewChain(UserDto userDto) {
    setUserDto(userDto);
    viewChainMap.put(1, new CreateAccountViewChain(getUserDto()));
    viewChainMap.put(2, new ListAccountViewChain(getUserDto()));
    viewChainMap.put(3, this);
    viewChainMap.put(4, new FinishViewChain());
  }

  @Override
  public void handle() {
    System.out.println("Выберите действие:\n1.Создать счет\n2.Действие над существующим счетом\n"
            + "3.На главную\n4.Выйти");
    Scanner scanner = getScanner();
    int answer = scanner.nextInt();
    scanner.nextLine();
    ViewChain viewChain = viewChainMap.get(answer);

    setNextView(viewChain != null ? viewChain : new UnknownViewChain(this));
  }
}
