package by.toukach.cleverbank.service.impl;

import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.exception.DBException;
import by.toukach.cleverbank.exception.ExceptionMessage;
import by.toukach.cleverbank.exception.InsufficientFundsException;
import by.toukach.cleverbank.exception.SleepException;
import by.toukach.cleverbank.repository.impl.DBInitializerImpl;
import by.toukach.cleverbank.service.AccountService;
import by.toukach.cleverbank.service.PercentageService;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.yaml.snakeyaml.Yaml;

public class PercentageServiceImpl implements PercentageService {

  private static final PercentageService instance = new PercentageServiceImpl();
  private static final String PERCENTAGE_FILE_PATH = "application.yml";

  private final AccountService accountService;

  private PercentageServiceImpl() {
    accountService = AccountServiceImpl.getInstance();
  }

  @Override
  public void run() {
    while (true) {
      try {
        TimeUnit.SECONDS.sleep(30);
      } catch (InterruptedException e) {
        throw new SleepException(ExceptionMessage.THREAD_SLEEP_MESSAGE, e);
      }

      LocalDateTime now = LocalDateTime.now();
      LocalDate localDate = now.toLocalDate();
      int dayOfMonth = localDate.getDayOfMonth();
      int lengthOfMonth = localDate.lengthOfMonth();
      LocalTime time = now.toLocalTime();
      LocalTime endOfDay = LocalTime.of(23, 59, 59);

      if (dayOfMonth == lengthOfMonth &&
          time.isAfter(endOfDay.minusSeconds(30)) && time.isBefore(endOfDay)) {
        double percentage = 0.0;

        try (InputStream inputStream = PercentageServiceImpl.class.getClassLoader()
            .getResourceAsStream(PERCENTAGE_FILE_PATH)) {
          Yaml yaml = new Yaml();
          Map<String, Double> data = yaml.load(inputStream);
          percentage = data.getOrDefault("percentage", 0.001);
        } catch (IOException e) {
          throw new InsufficientFundsException(ExceptionMessage.PERCENTAGE_FILE_MESSAGE);
        }

        List<AccountDto> accountDtos = accountService.readAll();

        for (AccountDto accountDto : accountDtos) {
          synchronized (accountService) {
            Double sum = accountDto.getSum();
            sum *= percentage;
            accountDto.setSum(sum);

            try (Connection connection =
                DBInitializerImpl.getInstance().getDataSource().getConnection()) {
              accountService.update(accountDto, connection);
            } catch (SQLException e) {
              throw new DBException(ExceptionMessage.DB_CONNECT_MESSAGE, e);
            }
          }
        }
      }
    }
  }

  public static PercentageService getInstance() {
    return instance;
  }
}
