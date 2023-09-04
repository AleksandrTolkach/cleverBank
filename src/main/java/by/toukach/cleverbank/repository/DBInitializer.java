package by.toukach.cleverbank.repository;

import javax.sql.DataSource;

/**
 * Интерфейс для настройки пула соединений к базе и предоставляющий DataSource.
 * */
public interface DBInitializer {

  /**
   * Метод предоставляющий доступ к настроенному DataSource.
   *
   * @return настроенный DataSource.
   */
  DataSource getDataSource();
}
