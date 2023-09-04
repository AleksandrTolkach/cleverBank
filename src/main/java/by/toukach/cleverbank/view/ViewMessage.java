package by.toukach.cleverbank.view;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Класс, содержащий сообщения для форм.
 * */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ViewMessage {

  public static final String ACTION_LIST_MESSAGE =
      "Выберите действие:\n1.Создать счет\n2.Действие над существующим счетом\n"
          + "3.На главную\n4.Выйти";
  public static final String ACCOUNT_NUMBER_MESSAGE = "Введите название счета";
  public static final String ENTRY_MESSAGE =
      "Приветствуем!\nВыберите действие:\n1.Войти\n2.Зарегистрироваться\n3.Выйти";
  public static final String USER_DETAIL = "ID = %s, название = %s, баланс = %s";
  public static final String ACCOUNT_ACTION_MESSAGE =
      "Выберите действие\n1.Пополнить баланс\n2.Списать со счета\n3.Перевод\n4.На главную\n5.Выход";
  public static final String WAITER_MESSAGE = "Чтобы войти введите любой символ";
  public static final String ACCOUNTS_NOT_EXIST_MESSAGE = "У вас нет счетов";
  public static final String ACCOUNT_CHOOSE_MESSAGE = "Выберите счет:";
  public static final String WRONG_OPTION_MESSAGE = "Выбран неверный вариант\n";
  public static final String LOGIN_MESSAGE = "Введите логин";
  public static final String PASSWORD_MESSAGE = "Введите пароль";
  public static final String SUM_MESSAGE = "Введите сумму";
  public static final String NAME_MESSAGE = "Введите имя";
  public static final String SURNAME_MESSAGE = "Введите фамилию";
  public static final String RECEIVER_ACCOUNT_MESSAGE = "Введите счет получателя";
}
