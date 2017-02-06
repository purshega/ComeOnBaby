package com.ComeOnBaby.dao;



import java.util.List;

/**
 * Created by ADM on 17.01.2017.
 */
public interface  EstimateDao {

    public Long create(Estimate estimate);
    Estimate read(Long id);
    void update(Estimate estimate);
    void delete(Estimate estimate);
    List<Estimate> findAll();

}
