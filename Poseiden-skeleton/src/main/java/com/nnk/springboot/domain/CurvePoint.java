package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
  // TODO: Map columns in data table CURVEPOINT with corresponding java fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "curve_id")
  private Integer curveId;

  private double term;
  private double value;

  @Column(name = "as_of_date")
  private Timestamp asOfDate;

  @Column(name = "creation_date")
  private Timestamp creationDate;

  public CurvePoint(Integer id, Integer curveId, double term, double value) {
    this.id = id;
    this.curveId = curveId;
    this.term = term;
    this.value = value;
  }

  public CurvePoint(final Integer curveIdParam, final double termParam, final double valueParam) {
    curveId = curveIdParam;
    term = termParam;
    value = valueParam;
  }

  public CurvePoint() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer idParam) {
    id = idParam;
  }

  public Integer getCurveId() {
    return curveId;
  }

  public void setCurveId(final Integer curveIdParam) {
    curveId = curveIdParam;
  }

  public double getTerm() {
    return term;
  }

  public void setTerm(final double termParam) {
    term = termParam;
  }

  public double getValue() {
    return value;
  }

  public Timestamp getAsOfDate() {
    return asOfDate;
  }

  public void setAsOfDate(final Timestamp asOfDateParam) {
    asOfDate = asOfDateParam;
  }

  public Timestamp getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(final Timestamp creationDateParam) {
    creationDate = creationDateParam;
  }

  public void setValue(final double valueParam) {
    value = valueParam;
  }
}
