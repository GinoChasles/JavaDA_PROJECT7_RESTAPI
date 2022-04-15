package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String json;
    private String template;
    private String sqlStr;
    private String sqlPart;

  public RuleName(final String nameParam, final String descriptionParam, final String jsonParam,
                  final String templateParam, final String sqlStrParam, final String sqlPartParam) {
    name = nameParam;
    description = descriptionParam;
    json = jsonParam;
    template = templateParam;
    sqlStr = sqlStrParam;
    sqlPart = sqlPartParam;
  }

  public RuleName() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer idParam) {
    id = idParam;
  }

  public String getName() {
    return name;
  }

  public void setName(final String nameParam) {
    name = nameParam;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String descriptionParam) {
    description = descriptionParam;
  }

  public String getJson() {
    return json;
  }

  public void setJson(final String jsonParam) {
    json = jsonParam;
  }

  public String getTemplate() {
    return template;
  }

  public void setTemplate(final String templateParam) {
    template = templateParam;
  }

  public String getSqlStr() {
    return sqlStr;
  }

  public void setSqlStr(final String sqlStrParam) {
    sqlStr = sqlStrParam;
  }

  public String getSqlPart() {
    return sqlPart;
  }

  public void setSqlPart(final String sqlPartParam) {
    sqlPart = sqlPartParam;
  }
}
