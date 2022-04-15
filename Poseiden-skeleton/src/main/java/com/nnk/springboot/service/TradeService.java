package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import java.util.List;

/**
 * The interface Trade service.
 */
public interface TradeService {
  /**
   * Find all list.
   *
   * @return the list
   */
  List<Trade> findAll();

  /**
   * Insert list.
   *
   * @param tradeParam the trade param
   * @return the list
   */
  List<Trade> insert(final Trade tradeParam);

  /**
   * Gets trade by id.
   *
   * @param id the id
   * @return the trade by id
   */
  Trade getTradeById(final Integer id);

  /**
   * Update list.
   *
   * @param id         the id
   * @param tradeParam the trade param
   * @return the list
   */
  List<Trade> update(final Integer id, final Trade tradeParam);

  /**
   * Delete list.
   *
   * @param id the id
   * @return the list
   */
  List<Trade> delete(final Integer id);
}
