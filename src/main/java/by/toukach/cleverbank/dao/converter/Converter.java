package by.toukach.cleverbank.dao.converter;

/**
 * Интерфейс для конвертации DTO в DAO и обратно.
 *
 * @param <D> DTO
 * @param <E> DAO
 */
public interface Converter<D, E> {

  /**
   * Метод, позволяющий конвертировать DAO в DTO.
   *
   * @param dao DAO для конвертации.
   * @return конвертированный в DTO объект.
   */
  D toDto(E dao);

  /**
   * Метод, позволяющий конвертировать DTO в DAO.
   *
   * @param dto DTO для конвертации.
   * @return конвертированный в DAO объект.
   */
  E toEntity(D dto);
}
