package by.toukach.cleverbank.view;

/**
 * Класс для вывода формы о некорректности вводных данных в консоль.
 * */
public class UnknownViewChain extends ViewChain {

  public UnknownViewChain(ViewChain viewChain) {
    setNextView(viewChain);
  }

  @Override
  public void handle() {
    System.out.println(ViewMessage.WRONG_OPTION_MESSAGE);
  }
}
