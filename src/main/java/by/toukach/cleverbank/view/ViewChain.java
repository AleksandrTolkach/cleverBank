package by.toukach.cleverbank.view;

import by.toukach.cleverbank.dto.UserDto;
import java.util.Scanner;

public abstract class ViewChain {

  private ViewChain nextViewChain;
  private UserDto userDto;
  private Scanner scanner = new Scanner(System.in);

  public ViewChain getNextView() {
    return nextViewChain;
  }

  public void setNextView(ViewChain nextViewChain) {
    this.nextViewChain = nextViewChain;
  }

  public UserDto getUserDto() {
    return userDto;
  }

  public void setUserDto(UserDto userDto) {
    this.userDto = userDto;
  }

  public Scanner getScanner() {
    return scanner;
  }

  public void setScanner(Scanner scanner) {
    this.scanner = scanner;
  }

  public abstract void handle();
}
