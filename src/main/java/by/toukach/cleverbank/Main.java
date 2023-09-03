package by.toukach.cleverbank;

import by.toukach.cleverbank.exception.ExceptionMessage;
import by.toukach.cleverbank.service.PercentageService;
import by.toukach.cleverbank.service.impl.PercentageServiceImpl;
import by.toukach.cleverbank.view.EntryViewChain;
import by.toukach.cleverbank.view.ViewChain;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    PercentageService percentageService = PercentageServiceImpl.getInstance();

    executorService.submit(
        () -> {
          ViewChain viewChain = new EntryViewChain();
          while (true) {
            try {
              viewChain.handle();

            } catch (InputMismatchException e) {
              System.out.println(ExceptionMessage.WRONG_SYMBOL_MESSAGE);
              viewChain.setScanner(new Scanner(System.in));
              viewChain.setNextView(viewChain);
            }
            viewChain = viewChain.getNextView();
          }
        });

    executorService.submit(percentageService::run);
  }
}
