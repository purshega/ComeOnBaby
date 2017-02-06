package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.Fertilization_guideDao;
import com.ComeOnBaby.model.Fertilization_guide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("fertilization_guideService")
public class Fertilization_guideServiceImpl implements Fertilization_guideService {
    private Fertilization_guideDao fertilization_guideDao;

    @Autowired(required = true)
    public void setCaseDao(Fertilization_guideDao fertilization_guideDao) {
        this.fertilization_guideDao = fertilization_guideDao;
    }

    @Override
    @Transactional
    public void addNewFertilization_guide(Fertilization_guide fertilization_guide) {
        fertilization_guideDao.create(fertilization_guide);
    }

    @Override
    @Transactional
    public void updateFertilization_guide(Fertilization_guide fertilization_guide) {
        fertilization_guideDao.update(fertilization_guide);
    }

    @Override
    @Transactional
    public void deleteFertilization_guide(Fertilization_guide fertilization_guide) {
        fertilization_guideDao.delete(fertilization_guide);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Fertilization_guide> getAllFertilization_guide() {
        return fertilization_guideDao.findAll();
    }
}