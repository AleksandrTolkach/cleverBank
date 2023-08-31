package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.dto.TransactionDto;
import by.toukach.cleverbank.dto.UserDto;
import by.toukach.cleverbank.enumiration.TransactionType;
import by.toukach.cleverbank.exception.InsufficientFundsException;
import by.toukach.cleverbank.service.TransactionHandler;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TransferAccountViewChain extends TransactionViewChain {

  public TransferAccountViewChain(AccountDto accountDto, UserDto userDto) {
    setAccountDto(accountDto);
    setUserDto(userDto);
  }

  @Override
  public void handle() {
    System.out.println("Введите счет получателя");
    Scanner scanner = getScanner();
    long receiverAccountId = scanner.nextLong();
    scanner.nextLine();
    System.out.println("Введите сумму перевода");
    long value = scanner.nextLong();

    AccountDto receiverAccountDto = getAccountService().read(receiverAccountId);

    TransactionDto transactionDto = TransactionDto.builder()
        .date(LocalDateTime.now())
        .type(TransactionType.TRANSFER)
        .senderBankId(getAccountDto().getBankId())
        .receiverBankId(receiverAccountDto.getBankId())
        .senderAccountId(getAccountDto().getId())
        .receiverAccountId(receiverAccountId)
        .value(value)
        .build();

    try {
      getTransactionHandlerFactory().getHandler(TransactionType.TRANSFER).handle(transactionDto);
    } catch (InsufficientFundsException e) {
      System.out.println(e.getMessage());
      setNextView(this);
    }

    AccountDto accountDto = getAccountService().read(getAccountDto().getId());

    setNextView(new SpecificAccountViewChain(accountDto, getUserDto()));
  }
}
