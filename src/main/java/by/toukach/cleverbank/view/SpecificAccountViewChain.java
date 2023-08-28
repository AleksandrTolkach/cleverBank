package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.dto.UserDto;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpecificAccountViewChain extends AccountViewChain {

  private Map<Integer, ViewChain> viewChainMap = new HashMap<>();

  public SpecificAccountViewChain(AccountDto accountDto, UserDto userDto) {
    setAccountDto(accountDto);
    viewChainMap.put(1, new ReceiveAccountViewChain(accountDto, userDto));
    viewChainMap.put(2, new SpendAccountViewChain(accountDto, userDto));
    viewChainMap.put(3, new TransferAccountViewChain(accountDto, userDto));
    viewChainMap.put(4, new AccountActionViewChain(userDto));
    viewChainMap.put(5, new FinishViewChain());
  }

  @Override
  public void handle() {
    System.out.println(String.format("ID = %s, название = %s, баланс = %s",
        getAccountDto().getId(), getAccountDto().getTitle(), getAccountDto().getSum()));
    System.out.println("Выберите действие:\n1.Пополнить баланс\n2.Списать со счета\n3.Перевод\n"
        + "4.На главную\n5.Выход");

    Scanner scanner = getScanner();
    int answer = scanner.nextInt();
    scanner.nextLine();

    ViewChain viewChain = viewChainMap.get(answer);

    setNextView(viewChain != null ? viewChain : new UnknownViewChain(this));
  }
}
