package by.toukach.cleverbank.view;

import java.util.Scanner;

public class WaitViewChain extends ViewChain {

  @Override
  public void handle() {
    System.out.println(ViewMessage.WAITER_MESSAGE);
    Scanner scanner = getScanner();
    scanner.nextLine();
    setNextView(new EntryViewChain());
  }
}
