package com.ComeOnBaby.service;

import com.ComeOnBaby.dao.AppUserDao;
import com.ComeOnBaby.dao.CityDao;
import com.ComeOnBaby.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by olegs on 17.02.2017.
 */

@Service("cityService")
@Transactional
public class CityServiceImpl implements CityService {
    private CityDao cityDao;

    @Autowired(required = true)
    public void setCaseDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public Long addNewCity(String name) {
        City city = new City();
        city.setName(name);
        return cityDao.create(city);
    }

    @Override
    public City findById(Long id) {
        return cityDao.read(id);
    }

    @Override
    public City findByName(String name) {
        List<City> cities = cityDao.findAll();
        for(City city : cities) {
            if(city.getName().equals(name)) {
                return city;
            }
        }
        return null;
    }

    @Override
    public void updateCity(City city) {
        cityDao.update(city);
    }

    @Override
    public void removeCity(City city) {
        cityDao.delete(city);
    }

    @Override
    public List<City> getAllCities() {
        return cityDao.findAll();
    }
}
