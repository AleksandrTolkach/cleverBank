package by.toukach.cleverbank;

import by.toukach.cleverbank.service.PercentageService;
import by.toukach.cleverbank.service.impl.PercentageServiceImpl;
import by.toukach.cleverbank.view.EntryViewChain;
import by.toukach.cleverbank.view.ViewChain;
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
            viewChain.handle();
            viewChain = viewChain.getNextView();
          }
        });

    executorService.submit(percentageService::run);
  }
}
