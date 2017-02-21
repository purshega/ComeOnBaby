package com.ComeOnBaby.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name="user_id", nullable=false)
    private Long user_id;

//    @NotEmpty
//    @DateTimeFormat
    @Column(name="date", nullable=false)
    private Date date;

    @Column(name="bbt")
    private Float bbt;

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

    @Column(name="has_exercise")
    private Boolean has_exercise;

    @Column(name="recommended_exercise")
    private String recommended_exercise;

    @Column(name="going_to_bed_from")
    private String going_to_bed_from;

    @Column(name="going_to_bed_to")
    private String going_to_bed_to;

    @Column(name="water_intake")
    private Double water_intake;

    @Column(name="heating_bathing")
    private Integer heating_bathing;

    @Column(name="vitamin")
    private Boolean vitamin;

    @Column(name="folic_acid")
    private Boolean folic_acid;

    @Column(name="coffee_intake")
    private Integer coffee_intake;

    @Column(name="alcohol_intake")
    private Integer alcohol_intake;

    @Column(name="smoking")
    private Boolean smoking;

    @Column(name="emotional_state")
    private Integer emotional_state;

    @Column(name="bmi")
    private Float bmi;


    public Note(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Float getBbt() {
        return bbt;
    }

    public void setBbt(Float bbt) {
        this.bbt = bbt;
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

    public Boolean getHas_exercise() {
        return has_exercise;
    }

    public void setHas_exercise(Boolean has_exercise) {
        this.has_exercise = has_exercise;
    }

    public String getRecommended_exercise() {
        return recommended_exercise;
    }

    public void setRecommended_exercise(String recommended_exercise) {
        this.recommended_exercise = recommended_exercise;
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

    public Double getWater_intake() {
        return water_intake;
    }

    public void setWater_intake(Double water_intake) {
        this.water_intake = water_intake;
    }

    public Integer getHeating_bathing() {
        return heating_bathing;
    }

    public void setHeating_bathing(Integer heating_bathing) {
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

    public Integer getCoffee_intake() {
        return coffee_intake;
    }

    public void setCoffee_intake(Integer coffee_intake) {
        this.coffee_intake = coffee_intake;
    }

    public Integer getAlcohol_intake() {
        return alcohol_intake;
    }

    public void setAlcohol_intake(Integer alcohol_intake) {
        this.alcohol_intake = alcohol_intake;
    }

    public Boolean getSmoking() {
        return smoking;
    }

    public void setSmoking(Boolean smoking) {
        this.smoking = smoking;
    }

    public Integer getEmotional_state() {
        return emotional_state;
    }

    public void setEmotional_state(Integer emotional_state) {
        this.emotional_state = emotional_state;
    }

    public Float getBmi() {
        return bmi;
    }

    public void setBmi(Float bmi) {
        this.bmi = bmi;
    }

    @Override
    public String toString() {
        return "Note{" +
                "user_id=" + user_id +
                ", date=" + date +
                ", bbt=" + bbt +
                ", recommended_food='" + recommended_food + '\'' +
                ", recommended_nuts='" + recommended_nuts + '\'' +
                ", has_nuts=" + has_nuts +
                ", recommended_tea='" + recommended_tea + '\'' +
                ", has_tea=" + has_tea +
                ", has_exercise=" + has_exercise +
                ", recommended_exercise='" + recommended_exercise + '\'' +
                ", going_to_bed_from='" + going_to_bed_from + '\'' +
                ", going_to_bed_to='" + going_to_bed_to + '\'' +
                ", water_intake=" + water_intake +
                ", heating_bathing=" + heating_bathing +
                ", vitamin=" + vitamin +
                ", folic_acid=" + folic_acid +
                ", coffee_intake=" + coffee_intake +
                ", alcohol_intake=" + alcohol_intake +
                ", smoking=" + smoking +
                ", emotional_state=" + emotional_state +
                ", bmi=" + bmi +
                '}';
    }
}
