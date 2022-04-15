package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service

public class RuleNameServiceImpl implements RuleNameService{
  final RuleNameRepository repository;

  public RuleNameServiceImpl(
      final RuleNameRepository repositoryParam) {
    repository = repositoryParam;
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  @Override
  public List<RuleName> findAll() {
    return repository.findAll();
  }

  /**
   * Insert list.
   *
   * @param ruleNameParam the rule name param
   * @return the list
   */
  @Override
  public List<RuleName> insert(final RuleName ruleNameParam) {
    repository.save(ruleNameParam);
    return repository.findAll();
  }

  /**
   * Gets rule nam by id.
   *
   * @param id the id
   * @return the rule nam by id
   */
  @Override
  public RuleName getRuleNamById(final Integer id) {
    Optional<RuleName> ruleNameLocal = repository.findById(id);
    RuleName result = new RuleName();
    if(ruleNameLocal.isPresent()) {
      result = ruleNameLocal.get();
    }
    return result;
  }

  /**
   * Update list.
   *
   * @param id            the id
   * @param ruleNameParam the rule name param
   * @return the list
   */
  @Override
  public List<RuleName> update(final Integer id, final RuleName ruleNameParam) {
    RuleName ruleNameLocal = this.getRuleNamById(id);
    ruleNameLocal.setName(ruleNameParam.getName());
    ruleNameLocal.setDescription(ruleNameParam.getDescription());
    ruleNameLocal.setJson(ruleNameParam.getJson());
    ruleNameLocal.setSqlPart(ruleNameParam.getSqlPart());
    ruleNameLocal.setSqlStr(ruleNameParam.getSqlStr());
    ruleNameLocal.setTemplate(ruleNameParam.getTemplate());
    repository.save(ruleNameLocal);

    return this.findAll();
  }

  /**
   * Delete list.
   *
   * @param id the id
   * @return the list
   */
  @Override
  public List<RuleName> delete(final Integer id) {
    RuleName ruleNameLocal = this.getRuleNamById(id);
    repository.delete(ruleNameLocal);
    return this.findAll();
  }
}
