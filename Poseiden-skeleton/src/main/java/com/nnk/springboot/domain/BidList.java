package com.nnk.springboot.domain;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList {
    // TODO: Map columns in data table BIDLIST with corresponding java fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer bidListId;

  private String account;
  private String type;

  private double bidQuantity;

  public BidList() {
  }

  public BidList(final int bidListIdParam, final String accountParam, final String typeParam,
                 final double bidQuantityParam) {
    bidListId = bidListIdParam;
    account = accountParam;
    type = typeParam;
    bidQuantity = bidQuantityParam;
  }

  public BidList(final String accountParam, final String typeParam, final double bidQuantityParam) {
    account = accountParam;
    type = typeParam;
    bidQuantity = bidQuantityParam;
  }

  public int getBidListId() {
    return bidListId;
  }

  public void setBidListId(final int bidListIdParam) {
    bidListId = bidListIdParam;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(final String accountParam) {
    account = accountParam;
  }

  public String getType() {
    return type;
  }

  public void setType(final String typeParam) {
    type = typeParam;
  }

  public double getBidQuantity() {
    return bidQuantity;
  }

  public void setBidQuantity(final double bidQuantityParam) {
    bidQuantity = bidQuantityParam;
  }
}
