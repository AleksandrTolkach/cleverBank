package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.UserDto;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс для вывода формы со списком действий в консоль.
 * */
public class AccountActionViewChain extends ViewChain {

  private final Map<Integer, ViewChain> viewChainMap = new HashMap<>();

  public AccountActionViewChain(UserDto userDto) {
    setUserDto(userDto);
    viewChainMap.put(1, new CreateAccountViewChain(getUserDto()));
    viewChainMap.put(2, new ListAccountViewChain(getUserDto()));
    viewChainMap.put(3, this);
    viewChainMap.put(4, new WaitViewChain());
  }

  @Override
  public void handle() {
    System.out.println(ViewMessage.ACTION_LIST_MESSAGE);
    Scanner scanner = getScanner();
    int answer = scanner.nextInt();
    scanner.nextLine();
    ViewChain viewChain = viewChainMap.get(answer);

    setNextView(viewChain != null ? viewChain : new UnknownViewChain(this));
  }
}
