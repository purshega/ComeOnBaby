package com.ComeOnBaby.service;



import com.ComeOnBaby.model.Estimate;

import java.util.List;


public interface EstimateService {
    void addNewEstimate(Estimate estimate);
    void updateEstimate(Estimate estimate);
    void deleteEstimate(Estimate estimate);
    List<Estimate> getAllEstimates();
}
