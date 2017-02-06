package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.EstimateDao;
import com.ComeOnBaby.model.Estimate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Alex on 1/18/2017.
 */

@Service("estimateService")
public class EstimateServiceImpl implements EstimateService {

    private EstimateDao estimateDao;

    @Autowired(required = true)
    public void setEstimateDao(EstimateDao estimateDao) {
        this.estimateDao = estimateDao;
    }

    @Override
    @Transactional
    public void addNewEstimate(Estimate estimate) {
        estimateDao.create(estimate);
    }

    @Override
    @Transactional
    public void updateEstimate(Estimate estimate) {
        estimateDao.update(estimate);
    }

    @Override
    @Transactional
    public void deleteEstimate(Estimate estimate) {
        estimateDao.delete(estimate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Estimate> getAllEstimates() {
        return estimateDao.findAll();
    }

}
