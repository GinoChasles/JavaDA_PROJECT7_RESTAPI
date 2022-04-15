package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;


@Entity
@Table(name = "trade")
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer tradeId;
  private String account;
  private String type;
  //TODO: validation
  private double buyQuantity;
  private double sellQuantity;
  private double buyPrice;
  private double sellPrice;
  private String benchmark;
  private Timestamp tradeDate;
  private String security;
  private String status;
  private String trader;
  private String book;
  private String creationName;
  private Timestamp creationDate;
  private String revisionName;
  private Timestamp revisionDate;
  private String dealName;
  private String dealType;
  private String sourceListId;
  private String side;

  public Trade() {
  }

  public Trade(final String accountParam, final String typeParam) {
    account = accountParam;
    type = typeParam;
  }

  public Integer getTradeId() {
    return tradeId;
  }

  public void setTradeId(final Integer tradeIdParam) {
    tradeId = tradeIdParam;
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
}
