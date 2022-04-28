package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "moodys_rating")
  private String moodysRating;

  @Column(name = "sand_p_rating")
  private String sandPRating;

  @Column(name = "fitch_rating")
  private String fitchRating;

  @Column(name = "order_number")
  private Integer orderNumber;

  public Rating(final String moodysRatingParam, final String sandPRatingParam,
                final String fitchRatingParam,
                final Integer orderNumberParam) {
    moodysRating = moodysRatingParam;
    sandPRating = sandPRatingParam;
    fitchRating = fitchRatingParam;
    orderNumber = orderNumberParam;
  }

  public Rating() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer idParam) {
    id = idParam;
  }

  public String getMoodysRating() {
    return moodysRating;
  }

  public void setMoodysRating(final String moodysRatingParam) {
    moodysRating = moodysRatingParam;
  }

  public String getSandPRating() {
    return sandPRating;
  }

  public void setSandPRating(final String sandPRatingParam) {
    sandPRating = sandPRatingParam;
  }

  public String getFitchRating() {
    return fitchRating;
  }

  public void setFitchRating(final String fitchRatingParam) {
    fitchRating = fitchRatingParam;
  }

  public Integer getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(final Integer orderNumberParam) {
    orderNumber = orderNumberParam;
  }
}
