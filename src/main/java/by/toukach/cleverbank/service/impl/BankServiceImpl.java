package by.toukach.cleverbank.service.impl;

import by.toukach.cleverbank.dao.Bank;
import by.toukach.cleverbank.dao.converter.Converter;
import by.toukach.cleverbank.dao.converter.impl.BankConverter;
import by.toukach.cleverbank.dto.BankDto;
import by.toukach.cleverbank.repository.BankRepository;
import by.toukach.cleverbank.repository.impl.BankRepositoryImpl;
import by.toukach.cleverbank.service.BankService;

/**
 * Класс для выполнения операций с банками.
 * */
public class BankServiceImpl implements BankService {

  private static final BankService instance = new BankServiceImpl();

  private final BankRepository bankRepository;
  private final Converter<BankDto, Bank> bankConverter;

  private BankServiceImpl() {
    bankRepository = BankRepositoryImpl.getInstance();
    bankConverter = BankConverter.getInstance();
  }

  @Override
  public BankDto read(Long id) {
    return bankConverter.toDto(bankRepository.read(id));
  }

  public static BankService getInstance() {
    return instance;
  }
}
