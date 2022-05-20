package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service

public class RatingServiceImpl implements RatingService{
  final RatingRepository repository;

  public RatingServiceImpl(final RatingRepository repositoryParam) {
    repository = repositoryParam;
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  @Override
  public List<Rating> findAll() {
    return repository.findAll();
  }

  /**
   * Insert list.
   *
   * @param ratingParam the rating param
   * @return the list
   */
  @Override
  public List<Rating> insert(final Rating ratingParam) {
    repository.save(ratingParam);
    return this.findAll();
  }

  /**
   * Gets rating by id.
   *
   * @param id the id
   * @return the rating by id
   */
  @Override
  public Rating getRatingById(final Integer id) {
    Optional<Rating> optionalRatingLocal = repository.findById(id);
    Rating result = new Rating();
    if(optionalRatingLocal.isPresent()) {
      result = optionalRatingLocal.get();
    } else {
      throw new IllegalArgumentException("Invalid id:" + id);
    }
    return result;
  }

  /**
   * Update list.
   *
   * @param id          the id
   * @param ratingParam the rating param
   * @return the list
   */
  @Override
  public List<Rating> update(final Integer id, final Rating ratingParam) {
    Rating ratingLocal = this.getRatingById(id);
    ratingLocal.setFitchRating(ratingParam.getFitchRating());
    ratingLocal.setSandPRating(ratingParam.getSandPRating());
    ratingLocal.setMoodysRating(ratingParam.getMoodysRating());
    ratingLocal.setOrderNumber(ratingParam.getOrderNumber());
    repository.save(ratingLocal);
    return this.findAll();
  }

  /**
   * Delete list.
   *
   * @param id the id
   * @return the list
   */
  @Override
  public List<Rating> delete(final Integer id) {
    Rating ratingLocal = this.getRatingById(id);
    repository.delete(ratingLocal);
    return this.findAll();
  }
}
