package com.ComeOnBaby.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "basic_question")
public class BasicQuestions {

    @Id
    @NotEmpty
    @Column(name="user_id", nullable=false)
    private Long user_id;

    @Column(name="passed_questions")
    private Boolean passed_questions;

    @Column(name="years_not_been_pregnant")
    private String years_not_been_pregnan;

    @Column(name="children")
    private String children;

    @Column(name="treatmentt")
    private String treatment;

    @Column(name="causes_infertility_female")
    private String causes_infertility_female;

    @Column(name="causes_infertility_malet")
    private String causes_infertility_male;

    @Column(name="need_help")
    private String need_help;

    @Column(name="plans_for_family")
    private String plans_for_family;

    public BasicQuestions(){

    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Boolean getPassed_questions() {
        return passed_questions;
    }

    public void setPassed_questions(Boolean passed_questions) {
        this.passed_questions = passed_questions;
    }

    public String getYears_not_been_pregnan() {
        return years_not_been_pregnan;
    }

    public void setYears_not_been_pregnan(String years_not_been_pregnan) {
        this.years_not_been_pregnan = years_not_been_pregnan;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getCauses_infertility_female() {
        return causes_infertility_female;
    }

    public void setCauses_infertility_female(String causes_infertility_female) {
        this.causes_infertility_female = causes_infertility_female;
    }

    public String getCauses_infertility_male() {
        return causes_infertility_male;
    }

    public void setCauses_infertility_male(String causes_infertility_male) {
        this.causes_infertility_male = causes_infertility_male;
    }

    public String getNeed_help() {
        return need_help;
    }

    public void setNeed_help(String need_help) {
        this.need_help = need_help;
    }

    public String getPlans_for_family() {
        return plans_for_family;
    }

    public void setPlans_for_family(String plans_for_family) {
        this.plans_for_family = plans_for_family;
    }

    public BasicQuestions(Long user_id, Boolean passed_questions, String years_not_been_pregnan, String children,
                          String treatment, String causes_infertility_female, String causes_infertility_male,
                          String need_help, String plans_for_family) {
        this.user_id = user_id;
        this.passed_questions = passed_questions;
        this.years_not_been_pregnan = years_not_been_pregnan;
        this.children = children;
        this.treatment = treatment;
        this.causes_infertility_female = causes_infertility_female;
        this.causes_infertility_male = causes_infertility_male;
        this.need_help = need_help;
        this.plans_for_family = plans_for_family;
    }
}
