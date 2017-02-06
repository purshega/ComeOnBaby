package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.Fertilization_guide;

import java.util.List;

public interface Fertilization_guideDao {
    Long create(Fertilization_guide fertilization_guide);
    Fertilization_guide read(Long id);
    void update(Fertilization_guide fertilization_guide);
    void delete(Fertilization_guide fertilization_guide);
    List<Fertilization_guide> findAll();

}
