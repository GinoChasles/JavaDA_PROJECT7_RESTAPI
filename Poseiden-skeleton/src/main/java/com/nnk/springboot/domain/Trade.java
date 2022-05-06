package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Timestamp;


@Entity
@Table(name = "trade")
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "trade_id")
  @Positive(message = "must be positive")
  private Integer tradeId;

  private String account;
  private String type;
  //TODO: validation
  @Column(name = "buy_quantity")
  @PositiveOrZero(message = "must be positive or equals to 0")
  private double buyQuantity;

  @Column(name = "sell_quantity")
  @PositiveOrZero(message = "must be positive or equals to 0")
  private double sellQuantity;

  @Column(name = "buy_price")
  @PositiveOrZero(message = "must be positive or equals to 0")
  private double buyPrice;

  @Column(name = "sell_price")
  @PositiveOrZero(message = "must be positive or equals to 0")
  private double sellPrice;

  private String benchmark;

  @Column(name = "trade_date")
  private Timestamp tradeDate;

  private String security;
  private String status;
  private String trader;
  private String book;
  @Column(name = "creation_name")
  private String creationName;

  @Column(name = "creation_date")
  private Timestamp creationDate;

  @Column(name = "revision_name")
  private String revisionName;

  @Column(name = "revision_date")
  private Timestamp revisionDate;

  @Column(name = "deal_name")
  private String dealName;

  @Column(name = "deal_type")
  private String dealType;

  @Column(name = "source_list_id")
  private String sourceListId;

  private String side;

  public Trade() {
  }

  public Trade(final String accountParam, final String typeParam) {
    account = accountParam;
    type = typeParam;
  }

  public Trade(Integer tradeId, String account, String type, double buyQuantity) {
    this.tradeId = tradeId;
    this.account = account;
    this.type = type;
    this.buyQuantity = buyQuantity;
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

  public double getBuyQuantity() {
    return buyQuantity;
  }

  public void setBuyQuantity(final double buyQuantityParam) {
    buyQuantity = buyQuantityParam;
  }

  public double getSellQuantity() {
    return sellQuantity;
  }

  public void setSellQuantity(final double sellQuantityParam) {
    sellQuantity = sellQuantityParam;
  }

  public double getBuyPrice() {
    return buyPrice;
  }

  public void setBuyPrice(final double buyPriceParam) {
    buyPrice = buyPriceParam;
  }

  public double getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(final double sellPriceParam) {
    sellPrice = sellPriceParam;
  }

  public String getBenchmark() {
    return benchmark;
  }

  public void setBenchmark(final String benchmarkParam) {
    benchmark = benchmarkParam;
  }

  public Timestamp getTradeDate() {
    return tradeDate;
  }

  public void setTradeDate(final Timestamp tradeDateParam) {
    tradeDate = tradeDateParam;
  }

  public String getSecurity() {
    return security;
  }

  public void setSecurity(final String securityParam) {
    security = securityParam;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(final String statusParam) {
    status = statusParam;
  }

  public String getTrader() {
    return trader;
  }

  public void setTrader(final String traderParam) {
    trader = traderParam;
  }

  public String getBook() {
    return book;
  }

  public void setBook(final String bookParam) {
    book = bookParam;
  }

  public String getCreationName() {
    return creationName;
  }

  public void setCreationName(final String creationNameParam) {
    creationName = creationNameParam;
  }

  public Timestamp getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(final Timestamp creationDateParam) {
    creationDate = creationDateParam;
  }

  public String getRevisionName() {
    return revisionName;
  }

  public void setRevisionName(final String revisionNameParam) {
    revisionName = revisionNameParam;
  }

  public Timestamp getRevisionDate() {
    return revisionDate;
  }

  public void setRevisionDate(final Timestamp revisionDateParam) {
    revisionDate = revisionDateParam;
  }

  public String getDealName() {
    return dealName;
  }

  public void setDealName(final String dealNameParam) {
    dealName = dealNameParam;
  }

  public String getDealType() {
    return dealType;
  }

  public void setDealType(final String dealTypeParam) {
    dealType = dealTypeParam;
  }

  public String getSourceListId() {
    return sourceListId;
  }

  public void setSourceListId(final String sourceListIdParam) {
    sourceListId = sourceListIdParam;
  }

  public String getSide() {
    return side;
  }

  public void setSide(final String sideParam) {
    side = sideParam;
  }
}
