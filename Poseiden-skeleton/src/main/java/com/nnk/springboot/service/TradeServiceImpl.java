package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl implements TradeService{
  final TradeRepository repository;

  public TradeServiceImpl(final TradeRepository repositoryParam) {
    repository = repositoryParam;
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  @Override
  public List<Trade> findAll() {
    return repository.findAll();
  }

  /**
   * Insert list.
   *
   * @param tradeParam the trade param
   * @return the list
   */
  @Override
  public List<Trade> insert(final Trade tradeParam) {
    repository.save(tradeParam);
    return this.findAll();
  }

  /**
   * Gets trade by id.
   *
   * @param id the id
   * @return the trade by id
   */
  @Override
  public Trade getTradeById(final Integer id) {
    Optional<Trade> tradeOptionalLocal = repository.findById(id);
    Trade result = new Trade();
    if(tradeOptionalLocal.isPresent()) {
      result = tradeOptionalLocal.get();
    }
    return result;
  }

  /**
   * Update list.
   *
   * @param id         the id
   * @param tradeParam the trade param
   * @return the list
   */
  @Override
  public List<Trade> update(final Integer id, final Trade tradeParam) {
    Trade tradeLocal = this.getTradeById(id);
    tradeLocal.setAccount(tradeParam.getAccount());
    tradeLocal.setType(tradeParam.getType());
    tradeLocal.setBuyQuantity(tradeParam.getBuyQuantity());
    repository.save(tradeLocal);
    return this.findAll();
  }

  /**
   * Delete list.
   *
   * @param id the id
   * @return the list
   */
  @Override
  public List<Trade> delete(final Integer id) {
    Trade tradeLocal = this.getTradeById(id);
    repository.delete(tradeLocal);
    return this.findAll();
  }
}
