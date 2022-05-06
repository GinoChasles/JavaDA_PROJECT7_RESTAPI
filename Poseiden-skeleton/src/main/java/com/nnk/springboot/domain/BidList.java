package com.nnk.springboot.domain;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList {
  // TODO: Map columns in data table BIDLIST with corresponding java fields
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "bid_list_id")
  @PositiveOrZero(message = "must be positive or equals to 0")
  private Integer BidListId;

  @Column(name = "account")
  private String account;

  @Column(name = "type")
  private String type;

  @Column(name = "bid_quantity")
  @PositiveOrZero(message = "must be positive or equals to 0")
  private double bidQuantity;

  @Column(name = "ask_quantity")
  @PositiveOrZero(message = "must be positive or equals to 0")
  private double askQuantity;

  @PositiveOrZero(message = "must be positive or equals to 0")
  private double bid;

  @PositiveOrZero(message = "must be positive or equals to 0")
  private double ask;

  private String benchmark;

  @Column(name = "bid_list_date")
  private Timestamp bidListDate;
  private String commentary;
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
  private String dealName;
  private String dealType;

  @Column(name = "source_list_id")
  private String sourceListId;
  private String side;


  public BidList() {
  }

  public BidList(final int bidListIdParam, final String accountParam, final String typeParam,
                 final double bidQuantityParam) {
    BidListId = bidListIdParam;
    account = accountParam;
    type = typeParam;
    bidQuantity = bidQuantityParam;
  }

  public BidList(final String accountParam, final String typeParam, final double bidQuantityParam) {
    account = accountParam;
    type = typeParam;
    bidQuantity = bidQuantityParam;
  }

  public Integer getBidListId() {
    return BidListId;
  }

  public void setAskQuantity(final double askQuantityParam) {
    askQuantity = askQuantityParam;
  }

  public void setBid(final double bidParam) {
    bid = bidParam;
  }

  public void setAsk(final double askParam) {
    ask = askParam;
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

  public void setBidListId(final Integer bidListIdParam) {
    BidListId = bidListIdParam;
  }

  public void setBidQuantity(final Double bidQuantityParam) {
    bidQuantity = bidQuantityParam;
  }

  public Double getAskQuantity() {
    return askQuantity;
  }

  public void setAskQuantity(final Double askQuantityParam) {
    askQuantity = askQuantityParam;
  }

  public Double getBid() {
    return bid;
  }

  public void setBid(final Double bidParam) {
    bid = bidParam;
  }

  public Double getAsk() {
    return ask;
  }

  public void setAsk(final Double askParam) {
    ask = askParam;
  }

  public String getBenchmark() {
    return benchmark;
  }

  public void setBenchmark(final String benchmarkParam) {
    benchmark = benchmarkParam;
  }

  public Timestamp getBidListDate() {
    return bidListDate;
  }

  public void setBidListDate(final Timestamp bidListDateParam) {
    bidListDate = bidListDateParam;
  }

  public String getCommentary() {
    return commentary;
  }

  public void setCommentary(final String commentaryParam) {
    commentary = commentaryParam;
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
