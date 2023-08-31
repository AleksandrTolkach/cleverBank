package by.toukach.cleverbank.view;

import java.util.Scanner;

public class FinishViewChain extends ViewChain {

  @Override
  public void handle() {
    System.out.println("Чтобы войти введите любой символ");
    Scanner scanner = getScanner();
    scanner.nextLine();
    setNextView(new EntryViewChain());
  }
}
