package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service

public class CurvePointServiceImpl implements CurvePointService {
  private final CurvePointRepository repository;

  public CurvePointServiceImpl(
      final CurvePointRepository repositoryParam) {
    repository = repositoryParam;
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  @Override
  public List<CurvePoint> findAll() {
    return repository.findAll();
  }

  /**
   * Insert list.
   *
   * @param curvePointParam the curve point param
   * @return the list
   */
  @Override
  public List<CurvePoint> insert(final CurvePoint curvePointParam) {
    repository.save(curvePointParam);
    return this.findAll();
  }

  /**
   * Gets curve point by id.
   *
   * @param id the id
   * @return the curve point by id
   */
  @Override
  public CurvePoint getCurvePointById(final Integer id) {
    Optional<CurvePoint> optionalCurvePointLocal = repository.findById(id);
    CurvePoint result = new CurvePoint();
    if(optionalCurvePointLocal.isPresent()) {
      result = optionalCurvePointLocal.get();
    } else {
      throw new IllegalArgumentException("Invalid id:" + id);
    }
    return result;
  }

  /**
   * Update list.
   *
   * @param id              the id
   * @param curvePointParam the curve point param
   * @return the list
   */
  @Override
  public List<CurvePoint> update(final Integer id, final CurvePoint curvePointParam) {
    CurvePoint curvePointLocal = this.getCurvePointById(id);
    curvePointLocal.setCurveId(curvePointParam.getCurveId());
    curvePointLocal.setTerm(curvePointParam.getTerm());
    curvePointLocal.setValue(curvePointParam.getValue());
    repository.save(curvePointLocal);
    return this.findAll();
  }

  /**
   * Delete list.
   *
   * @param id the id
   * @return the list
   */
  @Override
  public List<CurvePoint> delete(final Integer id) {
    CurvePoint curvePointLocal = this.getCurvePointById(id);
    repository.delete(curvePointLocal);
    return this.findAll();
  }
}
