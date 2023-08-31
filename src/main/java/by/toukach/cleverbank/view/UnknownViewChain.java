package by.toukach.cleverbank.view;

public class UnknownViewChain extends ViewChain {

  public UnknownViewChain(ViewChain viewChain) {
    setNextView(viewChain);
  }

  @Override
  public void handle() {
    System.out.println("Нет такого варианта");
  }
}