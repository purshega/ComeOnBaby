package com.ComeOnBaby.dao;

import com.ComeOnBaby.model.City;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by olegs on 17.02.2017.
 */
@Repository("cityDao")
public class CityDaoImpl extends AbstractDao<Long, City> implements CityDao{
    @Override
    public Long create(City city) {
        Long id = (Long) getSession().save(city);
        return id;
    }

    @Override
    public City read(Long id) {
        City city = (City) getSession().get(City.class, id);
        return city;
    }

    @Override
    public List<City> findAll() {
        Query query = getSession().createQuery("from City");
        return query.list();
    }
}
