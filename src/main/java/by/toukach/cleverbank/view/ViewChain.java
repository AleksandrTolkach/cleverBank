package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.UserDto;
import java.util.Scanner;

/**
 * Класс для вывода формы с данными в консоль.
 * */
public abstract class ViewChain {

  private ViewChain nextViewChain;
  private UserDto userDto;
  private Scanner scanner = new Scanner(System.in);

  /**
   * Вызов следующей формы с данными в цепочке.
   *
   * @return следующая форма с данными в цепочке.
   */
  public ViewChain getNextView() {
    return nextViewChain;
  }

  /**
   * Установка следующей формы с данными в цепочке.
   *
   * @param nextViewChain следующая форма с данными в цепочке.
   */
  public void setNextView(ViewChain nextViewChain) {
    this.nextViewChain = nextViewChain;
  }

  /**
   * Метод для вызова данных о пользователе.
   *
   * @return данные пользователя.
   */
  public UserDto getUserDto() {
    return userDto;
  }

  /**
   * Метод для внесения данных о пользователе в форму.
   *
   * @param userDto данные пользователя.
   */
  public void setUserDto(UserDto userDto) {
    this.userDto = userDto;
  }

  /**
   * Метод для вызова Scanner.
   *
   * @return запрашиваемый Scanner.
   */
  public Scanner getScanner() {
    return scanner;
  }

  /**
   * Установка Scanner в форму.
   *
   * @param scanner устанавливаемый Scanner.
   */
  public void setScanner(Scanner scanner) {
    this.scanner = scanner;
  }

  /**
   * Метод для обработки формы с данными.
   * */
  public abstract void handle();
}
