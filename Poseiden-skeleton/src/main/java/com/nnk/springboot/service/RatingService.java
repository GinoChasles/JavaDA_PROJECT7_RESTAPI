package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import java.util.List;

/**
 * The interface Rating service.
 */
public interface RatingService {
  /**
   * Find all list.
   *
   * @return the list
   */
  List<Rating> findAll();

  /**
   * Insert list.
   *
   * @param ratingParam the rating param
   * @return the list
   */
  List<Rating> insert(final Rating ratingParam);

  /**
   * Gets rating by id.
   *
   * @param id the id
   * @return the rating by id
   */
  Rating getRatingById(final Integer id);

  /**
   * Update list.
   *
   * @param id          the id
   * @param ratingParam the rating param
   * @return the list
   */
  List<Rating> update(final Integer id, final Rating ratingParam);

  /**
   * Delete list.
   *
   * @param id the id
   * @return the list
   */
  List<Rating> delete(final Integer id);
}
