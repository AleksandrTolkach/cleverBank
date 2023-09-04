package by.toukach.cleverbank.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Интерфейс для создания DAO из ResultSet.
 *
 * @param <T> объект создания.
 */
public interface RowMapper<T> {

  /**
   * Метод для создания DAO из ResultSet.
   *
   * @param resultSet полученный ResultSet из запроса.
   * @return созданный DAO.
   * @throws SQLException может быть выброшена при получении поля из ResultSet.
   */
  T mapRow(ResultSet resultSet) throws SQLException;
}
