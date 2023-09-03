package by.toukach.cleverbank.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessage {

  public static final String DB_REQUEST_MESSAGE = "Не удалось выполнить запрос в базу\n";
  public static final String DB_READ_MESSAGE = "Не удалось считать информацию из базы\n";
  public static final String DB_CONNECT_MESSAGE = "Ошибка подключения к базе\n";
  public static final String CHECK_SAVE_MESSAGE = "Не удалось сохранить чек\n";
  public static final String ACCOUNT_SAVE_MESSAGE = "Не удалось записать счет в базу\n";
  public static final String ACCOUNT_UPDATE_MESSAGE = "Не удалось обновить счет в базе\n";
  public static final String ACCOUNT_NOT_FOUND_MESSAGE = "Счет с id %s не найден\n";
  public static final String TRANSFER_LOOP_MESSAGE =
      "Нельзя проводить данную операцию над одним счетом\n";
  public static final String SPECIFIC_ACCOUNT_NOT_FOUND_MESSAGE =
      "Не удалось считать счет с %s %s из БД\n";
  public static final String TRANSACTION_SAVE_MESSAGE = "Не удалось записать транзакцию в базу\n";
  public static final String TRANSACTION_READ_MESSAGE = "Не считать транзакцию из базы\n";
  public static final String TRANSACTION_NOT_FOUND_MESSAGE = "Транзакции с id %s не найден\n";
  public static final String USER_SAVE_MESSAGE = "Не удалось записать пользователя в базу\n";
  public static final String USER_READ_MESSAGE = "Не удалось считать пользователя c %s %s из БД\n";
  public static final String USER_NOT_FOUND_MESSAGE = "Пользователь с %s %s не найден\n";
  public static final String TRANSACTION_ROLLBACK_MESSAGE = "Не удалось отменить транзакцию\n";
  public static final String BANK_NOT_FOUND_MESSAGE = "Банк с id %s не найден\n";
  public static final String WRONG_LOGIN_OR_PASSWORD_MESSAGE = "Неверный логин или пароль\n";
  public static final String PERCENTAGE_FILE_MESSAGE =
      "Не удалось считать данные из файла с процентами\n";
  public static final String INSUFFICIENT_FUNDS_MESSAGE = "На счете недостаточно средств\n";
  public static final String THREAD_SLEEP_MESSAGE = "Не удалось остановить поток\n";
  public static final String ENTITY_UPDATE_MESSAGE = "Запись устарела, повторите попытку позже\n";
  public static final String USER_DUPLICATE_MESSAGE =
      "Пользователь с таким логином уже существует\n";
  public static final String WRONG_SYMBOL_MESSAGE = "Использован неверный символ\n";
  public static final String POSITIVE_ARGUMENT_VALUE_MESSAGE =
      "Значение должно быть больше нуля\n";
}
