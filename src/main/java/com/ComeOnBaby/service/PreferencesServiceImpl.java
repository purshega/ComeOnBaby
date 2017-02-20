package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.PreferencesDao;
import com.ComeOnBaby.model.Preferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("preferencesService")
public class PreferencesServiceImpl implements PreferencesService{
    private PreferencesDao preferencesDao;

    @Autowired(required = true)
    public void setCaseDao(PreferencesDao preferencesDao) {
        this.preferencesDao = preferencesDao;
    }

    @Override
    @Transactional
    public void addNewPreferences(Preferences preferences) {
        preferencesDao.create(preferences);
    }

    @Override
    @Transactional
    public void updatePreferences(Preferences preferences) {
        preferencesDao.update(preferences);
    }

    @Override
    @Transactional
    public void deletePreferences(Preferences preferences) {
        preferencesDao.delete(preferences);
    }

    @Override
    @Transactional
    public Preferences findById(Long id) {
           return preferencesDao.read(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Preferences> getAllPreferences() {
        return preferencesDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Preferences findFirstByUserId(Long id){return preferencesDao.findFirstByUserId(id);}
}