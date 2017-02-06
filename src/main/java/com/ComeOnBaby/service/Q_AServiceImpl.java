package com.ComeOnBaby.service;

import com.ComeOnBaby.dao.Q_ADao;
import com.ComeOnBaby.model.Q_A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("q_aService")
public class Q_AServiceImpl implements Q_AService {
    private Q_ADao q_aDao;

    @Autowired(required = true)
    public void setCaseDao(Q_ADao q_aDao) {
        this.q_aDao = q_aDao;
    }

    @Override
    @Transactional
    public void addNewQ_A(Q_A q_a) {
        q_aDao.create(q_a);
    }

    @Override
    @Transactional
    public void updateQ_A(Q_A q_a) {
        q_aDao.update(q_a);
    }

    @Override
    @Transactional
    public void deleteQ_A(Q_A q_a) {
        q_aDao.delete(q_a);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Q_A> getAllQ_A() {
        return q_aDao.findAll();
    }
}
