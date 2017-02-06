package com.ComeOnBaby.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adm on 1/27/2017.
 */
@Service("statusService")
public class StatusServiceImpl implements StatusService {

    private StatusDao statusDao;

    @Autowired(required=true)
    public StatusServiceImpl(StatusDao statusDao) {
        this.statusDao = statusDao;
    }

    @Override
    @Transactional
    public void addNewStatus(Status status) {
        statusDao.create(status);
    }

    @Override
    @Transactional
    public void updateStatus(Status status) {
        statusDao.update(status);
    }

    @Override
    @Transactional
    public void deleteStatus(Status status) {
        statusDao.delete(status);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Status> getAllStatuses() {
        return statusDao.findAll();
    }
}
