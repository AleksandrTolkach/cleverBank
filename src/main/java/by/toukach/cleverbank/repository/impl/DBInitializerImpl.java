package by.toukach.cleverbank.repository.impl;

import by.toukach.cleverbank.exception.DBException;
import by.toukach.cleverbank.repository.DBInitializer;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import javax.sql.DataSource;

public class DBInitializerImpl implements DBInitializer {

  private volatile static DBInitializer instance;

  private ComboPooledDataSource cpds;

  private DBInitializerImpl() throws PropertyVetoException {
    cpds = new ComboPooledDataSource();
    cpds.setDriverClass("org.postgresql.Driver");
    cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/cleverbank");
    cpds.setUser("postgres");
    cpds.setPassword("postgres");
    cpds.setMinPoolSize(3);
    cpds.setAcquireIncrement(5);
    cpds.setMaxPoolSize(20);
    cpds.setMaxStatements(180);
  }

  @Override
  public DataSource getDataSource() {
    return cpds;
  }

  public static DBInitializer getInstance() {
    if (instance == null) {
      synchronized (DBInitializer.class) {
        if (instance == null) {
          try {
            instance = new DBInitializerImpl();
          } catch (PropertyVetoException e) {
            throw new DBException("Ошибка подключения к базе", e);
          }
        }
      }
    }
    return instance;
  }
}
