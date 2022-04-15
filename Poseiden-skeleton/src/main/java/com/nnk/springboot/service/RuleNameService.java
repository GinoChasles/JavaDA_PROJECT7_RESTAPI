package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import java.util.List;

/**
 * The interface Rule name service.
 */
public interface RuleNameService {
  /**
   * Find all list.
   *
   * @return the list
   */
  List<RuleName> findAll();

  /**
   * Insert list.
   *
   * @param ruleNameParam the rule name param
   * @return the list
   */
  List<RuleName> insert(final RuleName ruleNameParam);

  /**
   * Gets rule nam by id.
   *
   * @param id the id
   * @return the rule nam by id
   */
  RuleName getRuleNamById(final Integer id);

  /**
   * Update list.
   *
   * @param id            the id
   * @param ruleNameParam the rule name param
   * @return the list
   */
  List<RuleName> update(final Integer id, final RuleName ruleNameParam);

  /**
   * Delete list.
   *
   * @param id the id
   * @return the list
   */
  List<RuleName> delete(final Integer id);
}
