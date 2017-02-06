package com.ComeOnBaby.service;


import com.ComeOnBaby.model.Q_A;

import java.util.List;

public interface Q_AService {
    void addNewQ_A(Q_A q_a);
    void updateQ_A(Q_A q_a);
    void deleteQ_A(Q_A q_a);
    List<Q_A> getAllQ_A();
}
