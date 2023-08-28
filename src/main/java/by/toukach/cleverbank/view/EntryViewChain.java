package by.toukach.cleverbank.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EntryViewChain extends ViewChain {

  private Map<Integer, ViewChain> viewChainMap = new HashMap<>();

  public EntryViewChain() {
    viewChainMap.put(1, new LogInViewChain());
    viewChainMap.put(2, new SignUpViewChain());
    viewChainMap.put(3, new FinishViewChain());
  }

  @Override
  public void handle() {
    System.out.println("Приветствуем!\nВыберите действие:\n1.Войти\n2.Зарегистрироваться\n3.Выйти");
    Scanner scanner = getScanner();

    int answer = scanner.nextInt();
    scanner.nextLine();

    ViewChain viewChain = viewChainMap.get(answer);

    setNextView(viewChain != null ? viewChain : new UnknownViewChain(this));
  }
}
