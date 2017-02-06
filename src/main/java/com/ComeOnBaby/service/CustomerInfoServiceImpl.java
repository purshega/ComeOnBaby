package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.CustomerInfoDao;
import com.ComeOnBaby.model.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("customerInfoService")
public class CustomerInfoServiceImpl implements CustomerInfoService {

    private CustomerInfoDao customerInfoDao;

    @Autowired(required = true)
    public void setCustomerInfoDao(CustomerInfoDao customerInfoDao) {
        this.customerInfoDao = customerInfoDao;
    }


    @Override
    @Transactional
    public void addNewCustomerInfo(CustomerInfo customerInfo) {
        customerInfoDao.create(customerInfo);
    }

    @Override
    @Transactional
    public void updateCustomerInfo(CustomerInfo customerInfo) {
        customerInfoDao.update(customerInfo);
    }

    @Override
    @Transactional
    public void deleteCustomerInfo(CustomerInfo customerInfo) {
        customerInfoDao.delete(customerInfo);
    }

    @Override
    @Transactional
    public List<CustomerInfo> getAllCustomerInfos() {
        return customerInfoDao.findAll();
    }

    @Override
    @Transactional
    public CustomerInfo getCustomerInfoById(Long id) {
        CustomerInfo customerInfo = customerInfoDao.read(id);
        return customerInfo;
    }


}

