package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import java.util.List;

/**
 * The interface Bid list service.
 */
public interface BidListService {
  /**
   * Find all list.
   *
   * @return the list
   */
  List<BidList> findAll();

  /**
   * Gets bid list by id.
   *
   * @param id the id
   * @return the bid list by id
   */
  BidList getBidListById(final Integer id);

  /**
   * Update list.
   *
   * @param id           the id
   * @param bidListParam the bid list param
   * @return the list
   */
  List<BidList> update(final Integer id, final BidList bidListParam);

  /**
   * Insert list.
   *
   * @param bidListParam the bid list param
   * @return the list
   */
  List<BidList> insert(final BidList bidListParam);

  /**
   * Delete list.
   *
   * @param id the id
   * @return the list
   */
  List<BidList> delete(final Integer id);
}
