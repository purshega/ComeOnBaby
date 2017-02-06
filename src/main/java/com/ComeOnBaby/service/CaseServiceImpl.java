package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.CaseDao;
import com.ComeOnBaby.model.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("caseService")
public class CaseServiceImpl implements CaseService {

    private CaseDao caseDao;

    @Autowired(required=true)
    public void setCaseDao(CaseDao caseDao) {
        this.caseDao = caseDao;
    }

    @Override
    @Transactional
    public void addNewCase(Case aCase) {
        caseDao.create(aCase);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Case> getAllCases() {
        return caseDao.findAll();
    }

    @Override
    @Transactional
    public void updateCase(Case aCase) {
        caseDao.update(aCase);
    }

    @Override
    @Transactional
    public void deleteCase(Case aCase) {
        caseDao.delete(aCase);
    }

}
