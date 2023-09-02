package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.dto.TransactionDto;
import by.toukach.cleverbank.dto.UserDto;
import by.toukach.cleverbank.enumiration.TransactionType;
import by.toukach.cleverbank.exception.DBException;
import by.toukach.cleverbank.repository.impl.DBInitializerImpl;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ReceiveAccountViewChain extends TransactionViewChain {

  public ReceiveAccountViewChain(AccountDto accountDto, UserDto userDto) {
    setAccountDto(accountDto);
    setUserDto(userDto);
  }

  @Override
  public void handle() {
    System.out.println("Введите сумму");
    Long accountId = getAccountDto().getId();
    Scanner scanner = getScanner();
    double answer = scanner.nextDouble();
    scanner.nextLine();

    TransactionDto transactionDto = TransactionDto.builder()
        .receiverAccountId(accountId)
        .date(LocalDateTime.now())
        .type(TransactionType.RECEIVE)
        .senderBankId(getAccountDto().getBankId())
        .receiverBankId(getAccountDto().getBankId())
        .senderAccountId(accountId)
        .receiverAccountId(accountId)
        .value(answer)
        .build();

    getTransactionHandlerFactory().getHandler(TransactionType.RECEIVE).handle(transactionDto);

    setNextView(new SpecificAccountViewChain(getAccountService().read(accountId), getUserDto()));
  }
}
