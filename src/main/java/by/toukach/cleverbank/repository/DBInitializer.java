package by.toukach.cleverbank.repository;

import javax.sql.DataSource;

public interface DBInitializer {

  DataSource getDataSource();
}
