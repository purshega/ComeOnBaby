package com.ComeOnBaby.service;




import java.util.List;

public interface CaseService {
    void addNewCase(Case aCase);
    void updateCase(Case aCase);
    void deleteCase(Case aCase);
    List<Case> getAllCases();
}
