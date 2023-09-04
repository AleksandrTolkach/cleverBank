package by.toukach.cleverbank.enumiration;

/**
 * Перечисление возможных операций в банке.
 * */
public enum TransactionType {
  SPEND("Списание"),
  RECEIVE("Получение"),
  TRANSFER("Перевод");

  private final String value;

  TransactionType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
