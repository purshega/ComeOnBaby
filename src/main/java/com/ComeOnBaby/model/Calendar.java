package com.ComeOnBaby.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "calendar")
public class Calendar  {

    @NotEmpty
    @Column(name="user_id", nullable=false)
    private Long user_id;

    @NotEmpty
    @Column(name="date", nullable=false)
    private Date date;

    @Column(name="basic_body_temperature")
    private Float basic_body_temperature;

    @Column(name="recommended_food")
    private String recommended_food;

    @Column(name="recommended_nuts")
    private String recommended_nuts;

    @Column(name="has_nuts")
    private Boolean has_nuts;

    @Column(name="recommended_tea")
    private String recommended_tea;

    @Column(name="has_tea")
    private Boolean has_tea;

    @Column(name="going_to_bed_from")
    private String going_to_bed_from;

    @Column(name="going_to_bed_to")
    private String going_to_bed_to;

    @Column(name="water_intake")
    private String water_intake;

    @Column(name="heating_bathing")
    private String heating_bathing;

    @Column(name="vitamin")
    private Boolean vitamin;

    @Column(name="folic_acid")
    private Boolean folic_acid;

    @Column(name="alcohol_intake")
    private Boolean alcohol_intake;

    @Column(name="smoking")
    private Boolean smoking;

    @Column(name="emotional_state")
    private String 	emotional_state;

    @Column(name="bmi")
    private Float bmi;


    public Calendar(){

    }



    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getBasic_body_temperature() {
        return basic_body_temperature;
    }

    public void setBasic_body_temperature(Float basic_body_temperature) {
        this.basic_body_temperature = basic_body_temperature;
    }

    public String getRecommended_food() {
        return recommended_food;
    }

    public void setRecommended_food(String recommended_food) {
        this.recommended_food = recommended_food;
    }

    public String getRecommended_nuts() {
        return recommended_nuts;
    }

    public void setRecommended_nuts(String recommended_nuts) {
        this.recommended_nuts = recommended_nuts;
    }

    public Boolean getHas_nuts() {
        return has_nuts;
    }

    public void setHas_nuts(Boolean has_nuts) {
        this.has_nuts = has_nuts;
    }

    public String getRecommended_tea() {
        return recommended_tea;
    }

    public void setRecommended_tea(String recommended_tea) {
        this.recommended_tea = recommended_tea;
    }

    public Boolean getHas_tea() {
        return has_tea;
    }

    public void setHas_tea(Boolean has_tea) {
        this.has_tea = has_tea;
    }

    public String getGoing_to_bed_from() {
        return going_to_bed_from;
    }

    public void setGoing_to_bed_from(String going_to_bed_from) {
        this.going_to_bed_from = going_to_bed_from;
    }

    public String getGoing_to_bed_to() {
        return going_to_bed_to;
    }

    public void setGoing_to_bed_to(String going_to_bed_to) {
        this.going_to_bed_to = going_to_bed_to;
    }

    public String getWater_intake() {
        return water_intake;
    }

    public void setWater_intake(String water_intake) {
        this.water_intake = water_intake;
    }

    public String getHeating_bathing() {
        return heating_bathing;
    }

    public void setHeating_bathing(String heating_bathing) {
        this.heating_bathing = heating_bathing;
    }

    public Boolean getVitamin() {
        return vitamin;
    }

    public void setVitamin(Boolean vitamin) {
        this.vitamin = vitamin;
    }

    public Boolean getFolic_acid() {
        return folic_acid;
    }

    public void setFolic_acid(Boolean folic_acid) {
        this.folic_acid = folic_acid;
    }

    public Boolean getAlcohol_intake() {
        return alcohol_intake;
    }

    public void setAlcohol_intake(Boolean alcohol_intake) {
        this.alcohol_intake = alcohol_intake;
    }

    public Boolean getSmoking() {
        return smoking;
    }

    public void setSmoking(Boolean smoking) {
        this.smoking = smoking;
    }

    public String getEmotional_state() {
        return emotional_state;
    }

    public void setEmotional_state(String emotional_state) {
        this.emotional_state = emotional_state;
    }

    public Float getBmi() {
        return bmi;
    }

    public void setBmi(Float bmi) {
        this.bmi = bmi;
    }

    public Calendar(Long user_id, Date date, Float basic_body_temperature, String recommended_food, String recommended_nuts,
                    Boolean has_nuts, String recommended_tea, Boolean has_tea, String going_to_bed_from, String going_to_bed_to,
                    String water_intake, String heating_bathing, Boolean vitamin, Boolean folic_acid, Boolean alcohol_intake,
                    Boolean smoking, String emotional_state, Float bmi) {
        this.user_id = user_id;
        this.date = date;
        this.basic_body_temperature = basic_body_temperature;
        this.recommended_food = recommended_food;
        this.recommended_nuts = recommended_nuts;
        this.has_nuts = has_nuts;
        this.recommended_tea = recommended_tea;
        this.has_tea = has_tea;
        this.going_to_bed_from = going_to_bed_from;
        this.going_to_bed_to = going_to_bed_to;
        this.water_intake = water_intake;
        this.heating_bathing = heating_bathing;
        this.vitamin = vitamin;
        this.folic_acid = folic_acid;
        this.alcohol_intake = alcohol_intake;
        this.smoking = smoking;
        this.emotional_state = emotional_state;
        this.bmi = bmi;
    }
}
