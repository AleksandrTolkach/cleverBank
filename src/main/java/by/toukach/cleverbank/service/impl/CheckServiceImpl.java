package by.toukach.cleverbank.service.impl;

import by.toukach.cleverbank.dto.BankDto;
import by.toukach.cleverbank.dto.TransactionDto;
import by.toukach.cleverbank.exception.CheckException;
import by.toukach.cleverbank.exception.ExceptionMessage;
import by.toukach.cleverbank.service.BankService;
import by.toukach.cleverbank.service.CheckService;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для выполнения операций с чеком.
 * */
public class CheckServiceImpl implements CheckService {

  private static final CheckService instance = new CheckServiceImpl();
  private static final String CHECK_ID = "| Чек:              $place_holder$%s |%n";
  private static final String DATE_TIME = "| %s        $place_holder$%s |%n";
  private static final String TYPE = "| Тип транзакции:   $place_holder$%s |%n";
  private static final String SENDER_BANK = "| Банк отправителя: $place_holder$%s |%n";
  private static final String RECEIVER_BANK = "| Банк получателя:  $place_holder$%s |%n";
  private static final String SENDER_ACCOUNT = "| Счет отправителя: $place_holder$%s |%n";
  private static final String RECEIVER_ACCOUNT = "| Счет получателя:  $place_holder$%s |%n";
  private static final String SUM = "| Сумма:            $place_holder$%s BYN |%n";
  private static final String TITLE = "|%sБанковский счет%s|%n";
  private static final String FOLDER_NAME = "check/";

  private final BankService bankService;

  private CheckServiceImpl() {
    bankService = BankServiceImpl.getInstance();
  }

  @Override
  public void print(TransactionDto transaction) {
    String filePath = String.format("%s%s.txt", FOLDER_NAME, transaction.getId());

    BankDto senderBank = bankService.read(transaction.getSenderBankId());
    BankDto receiverBank = bankService.read(transaction.getReceiverBankId());

    try (FileWriter fileWriter = new FileWriter(filePath)) {
      LocalDateTime date = transaction.getDate();

      List<String> checkContent = new ArrayList<>();
      String checkId = String.format(CHECK_ID, transaction.getId());
      String dateTime =
          String.format(DATE_TIME, date.toLocalDate(), date.toLocalTime().withNano(0));
      String type = String.format(TYPE, transaction.getType().getValue());
      String senderBankName = String.format(SENDER_BANK, senderBank.getName());
      String receiverBankName = String.format(RECEIVER_BANK, receiverBank.getName());
      String senderAccount = String.format(SENDER_ACCOUNT, transaction.getSenderAccountId());
      String receiverAccount = String.format(RECEIVER_ACCOUNT, transaction.getReceiverBankId());
      String sum = String.format(SUM, transaction.getValue());

      checkContent.add(checkId);
      checkContent.add(dateTime);
      checkContent.add(type);
      checkContent.add(senderBankName);
      checkContent.add(receiverBankName);
      checkContent.add(senderAccount);
      checkContent.add(receiverAccount);
      checkContent.add(sum);

      int maxStringLength = checkContent.stream()
          .map(String::length)
          .max(Integer::compareTo)
          .orElseGet(() -> 0);

      List<String> modifiedContent = checkContent.stream()
          .map(s -> {
            int emptySpace = maxStringLength - s.length();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < emptySpace; i++) {
              stringBuilder.append(" ");
            }
            return s.replace("$place_holder$", stringBuilder.toString());
          })
          .toList();

      StringBuilder stringBuilder = new StringBuilder();
      modifiedContent.forEach(stringBuilder::append);

      int maxStringLengthWithoutHolder = maxStringLength - 15;

      int emptySpaceInTitleLength = (maxStringLengthWithoutHolder - 16) / 2;

      StringBuilder emptyStringInTitle = new StringBuilder();
      for (int i = 0; i < emptySpaceInTitleLength; i++) {
        emptyStringInTitle.append(" ");
      }

      String title = String.format(TITLE, emptyStringInTitle, emptyStringInTitle);

      stringBuilder.insert(0, title);
      stringBuilder.insert(0, "\n");
      for (int i = 0; i < maxStringLengthWithoutHolder; i++) {
        stringBuilder.insert(0, "-");
      }
      stringBuilder.append("|");
      for (int i = 0; i < (maxStringLengthWithoutHolder - 2); i++) {
        stringBuilder.append("-");
      }
      stringBuilder.append("|\n");

      fileWriter.write(stringBuilder.toString());
    } catch (IOException e) {
      throw new CheckException(ExceptionMessage.CHECK_SAVE_MESSAGE, e);
    }
  }

  public static CheckService getInstance() {
    return instance;
  }
}
