package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.AccountDto;
import by.toukach.cleverbank.dto.TransactionDto;
import by.toukach.cleverbank.dto.UserDto;
import by.toukach.cleverbank.enumiration.TransactionType;
import by.toukach.cleverbank.exception.ArgumentValueException;
import by.toukach.cleverbank.exception.EntityNotFoundException;
import by.toukach.cleverbank.exception.InsufficientFundsException;
import by.toukach.cleverbank.exception.TransferException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Класс для вывода формы по переводу средств в консоль.
 * */
public class TransferAccountViewChain extends TransactionViewChain {

  public TransferAccountViewChain(AccountDto accountDto, UserDto userDto) {
    setAccountDto(accountDto);
    setUserDto(userDto);
  }

  @Override
  public void handle() {
    System.out.println(ViewMessage.RECEIVER_ACCOUNT_MESSAGE);
    Scanner scanner = getScanner();
    long receiverAccountId = scanner.nextLong();
    scanner.nextLine();
    System.out.println(ViewMessage.SUM_MESSAGE);
    double value = scanner.nextDouble();

    AccountDto receiverAccountDto = null;
    try {
      receiverAccountDto = getAccountService().read(receiverAccountId);
    } catch (EntityNotFoundException e) {
      System.out.println(e.getMessage());
      setNextView(this);
      return;
    }

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
    } catch (InsufficientFundsException | TransferException | ArgumentValueException e) {
      System.out.println(e.getMessage());
      setNextView(this);
    }

    AccountDto accountDto = getAccountService().read(getAccountDto().getId());

    setNextView(new SpecificAccountViewChain(accountDto, getUserDto()));
  }
}
