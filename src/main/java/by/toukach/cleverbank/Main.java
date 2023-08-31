package by.toukach.cleverbank;

import by.toukach.cleverbank.service.AccountService;
import by.toukach.cleverbank.service.AuthService;
import by.toukach.cleverbank.service.UserService;
import by.toukach.cleverbank.service.impl.AccountServiceImpl;
import by.toukach.cleverbank.service.impl.AuthServiceImpl;
import by.toukach.cleverbank.service.impl.UserServiceImpl;
import by.toukach.cleverbank.view.EntryViewChain;
import by.toukach.cleverbank.view.ViewChain;

public class Main {

  private static final UserService userService = UserServiceImpl.getInstance();
  private static final AuthService authService = AuthServiceImpl.getInstance();
  private static final AccountService accountService = AccountServiceImpl.getInstance();

  public static void main(String[] args) {
    ViewChain viewChain = new EntryViewChain();

    while (true) {
      viewChain.handle();
      viewChain = viewChain.getNextView();
      System.out.println();
    }
  }
}
