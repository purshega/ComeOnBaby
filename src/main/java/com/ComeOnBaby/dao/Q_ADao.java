package com.ComeOnBaby.dao;



import com.ComeOnBaby.model.Q_A;

import java.util.List;

/**
 * Created by adm on 1/30/2017.
 */
public interface Q_ADao {
    Long create(Q_A Q_As);
    Q_A read(Long id);
    void update(Q_A Q_As);
    void delete(Q_A Q_As);
    List<Q_A> findAll();
}
