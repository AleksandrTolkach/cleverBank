package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.dto.UserDto;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Класс для вывода формы с данными о счете в консоль.
 * */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpecificAccountViewChain extends AccountViewChain {

  private final Map<Integer, ViewChain> viewChainMap = new HashMap<>();

  public SpecificAccountViewChain(AccountDto accountDto, UserDto userDto) {
    setAccountDto(accountDto);
    viewChainMap.put(1, new ReceiveAccountViewChain(accountDto, userDto));
    viewChainMap.put(2, new SpendAccountViewChain(accountDto, userDto));
    viewChainMap.put(3, new TransferAccountViewChain(accountDto, userDto));
    viewChainMap.put(4, new AccountActionViewChain(userDto));
    viewChainMap.put(5, new WaitViewChain());
  }

  @Override
  public void handle() {
    System.out.println(String.format(ViewMessage.USER_DETAIL,
        getAccountDto().getId(), getAccountDto().getTitle(), getAccountDto().getSum()));
    System.out.println(ViewMessage.ACCOUNT_ACTION_MESSAGE);

    Scanner scanner = getScanner();
    int answer = scanner.nextInt();
    scanner.nextLine();

    ViewChain viewChain = viewChainMap.get(answer);

    setNextView(viewChain != null ? viewChain : new UnknownViewChain(this));
  }
}
