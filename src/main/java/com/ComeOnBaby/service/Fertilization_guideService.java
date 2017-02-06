package com.ComeOnBaby.service;


import com.ComeOnBaby.model.Fertilization_guide;

import java.util.List;

public interface Fertilization_guideService {
    void addNewFertilization_guide(Fertilization_guide fertilization_guide);
    void updateFertilization_guide(Fertilization_guide fertilization_guide);
    void deleteFertilization_guide(Fertilization_guide fertilization_guide);
    List<Fertilization_guide> getAllFertilization_guide();
}
