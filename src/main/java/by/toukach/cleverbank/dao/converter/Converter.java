package by.toukach.cleverbank.dao.converter;

public interface Converter<D, E> {

  D toDto(E entity);

  E toEntity(D dto);
}
