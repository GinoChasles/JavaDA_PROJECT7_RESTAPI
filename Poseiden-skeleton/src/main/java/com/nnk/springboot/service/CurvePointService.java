package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import java.util.List;

/**
 * The interface Curve point service.
 */
public interface CurvePointService {
  /**
   * Find all list.
   *
   * @return the list
   */
  List<CurvePoint> findAll();

  /**
   * Insert list.
   *
   * @param curvePointParam the curve point param
   * @return the list
   */
  List<CurvePoint> insert(final CurvePoint curvePointParam);

  /**
   * Gets curve point by id.
   *
   * @param id the id
   * @return the curve point by id
   */
  CurvePoint getCurvePointById(final Integer id);

  /**
   * Update list.
   *
   * @param id              the id
   * @param curvePointParam the curve point param
   * @return the list
   */
  List<CurvePoint> update(final Integer id, final CurvePoint curvePointParam);

  /**
   * Delete list.
   *
   * @param id the id
   * @return the list
   */
  List<CurvePoint> delete(final Integer id);
}
