package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service

public class BidListServiceImpl implements BidListService {

  private final BidListRepository repository;

  public BidListServiceImpl(final BidListRepository repositoryParam) {
    repository = repositoryParam;
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  @Override
  public List<BidList> findAll() {
    return repository.findAll();
  }

  /**
   * Gets bid list by id.
   *
   * @param id the id
   * @return the bid list by id
   */
  @Override
  public BidList getBidListById(final Integer id) {
    Optional<BidList> optionalBidListLocal = repository.findById(id);
    BidList result = new BidList();

    if(optionalBidListLocal.isPresent()) {
      result = optionalBidListLocal.get();
    } else {
      throw new IllegalArgumentException("Invalid id:" + id);
    }

    return result;
  }

  /**
   * Update list.
   *
   * @param id           the id
   * @param bidListParam the bid list param
   * @return the list
   */
  @Override
  public List<BidList> update(final Integer id, final BidList bidListParam) {
    BidList bidListLocal = this.getBidListById(id);
    bidListLocal.setBidQuantity(bidListParam.getBidQuantity());
    bidListLocal.setAccount(bidListParam.getAccount());
    bidListLocal.setType(bidListParam.getType());
    repository.save(bidListLocal);
    List<BidList> bidListListLocal = this.findAll();
    return bidListListLocal;
  }

  /**
   * Insert list.
   *
   * @param bidListParam the bid list param
   * @return the list
   */
  @Override
  public List<BidList> insert(final BidList bidListParam) {
    repository.save(bidListParam);
    List<BidList> bidListListLocal = this.findAll();
    return bidListListLocal;
  }

  /**
   * Delete list.
   *
   * @param id the id
   * @return the list
   */
  @Override
  public List<BidList> delete(final Integer id) {
    BidList bidListLocal = this.getBidListById(id);
    repository.delete(bidListLocal);
    List<BidList> bidListListLocal = this.findAll();
    return bidListListLocal;
  }
}
