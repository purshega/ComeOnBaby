package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.BasicQuestions;

import java.util.List;

public interface BasicQuestionsDao {

    Long create(BasicQuestions questions);
    BasicQuestions read(Long id);
    void update(BasicQuestions questions);
    void delete(BasicQuestions questions);
    List<BasicQuestions> findAll();
}

