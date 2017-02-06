package com.ComeOnBaby.service;

import com.ComeOnBaby.model.BasicQuestions;

import java.util.List;

/**
 * Created by Home on 06.02.2017.
 */
public interface BasicQuestionsService {
    void addNewBasicQuestions(BasicQuestions basicQuestions);
    void updateBasicQuestions(BasicQuestions basicQuestions);
    void deleteBasicQuestions(BasicQuestions basicQuestions);
    List<BasicQuestions> getAllBasicQuestions();
}
