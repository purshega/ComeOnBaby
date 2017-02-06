package com.ComeOnBaby.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "preferences")

public class Preferences {

    @NotEmpty
    @Column(name="user_id", nullable=false)
    private Long user_id;

    @Column(name="city")
    private String city;

    @Column(name="nickname")
    private String nickname;

    @Column(name="gender")
    private String gender;

    @Column(name="birthday")
    private Integer birthday;

    @Column(name="adress")
    private String adress;

    @Column(name="menstrual_cycle")
    private Integer menstrual_cycle;

    @Column(name="red_days")
    private Integer red_days;

    @Column(name="last_menstruation_start_day")
    private Date last_menstruation_start_day;

    @Column(name="weigth")
    private Float weigth;

    @Column(name="height")
    private Float height;

    @Column(name="avatar")
    private String avatar;

    public Preferences(){

    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Integer getMenstrual_cycle() {
        return menstrual_cycle;
    }

    public void setMenstrual_cycle(Integer menstrual_cycle) {
        this.menstrual_cycle = menstrual_cycle;
    }

    public Integer getRed_days() {
        return red_days;
    }

    public void setRed_days(Integer red_days) {
        this.red_days = red_days;
    }

    public Date getLast_menstruation_start_day() {
        return last_menstruation_start_day;
    }

    public void setLast_menstruation_start_day(Date last_menstruation_start_day) {
        this.last_menstruation_start_day = last_menstruation_start_day;
    }

    public Float getWeigth() {
        return weigth;
    }

    public void setWeigth(Float weigth) {
        this.weigth = weigth;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Preferences(Long user_id, String city, String nickname, String gender, Integer birthday,
                       String adress, Integer menstrual_cycle, Integer red_days, Date last_menstruation_start_day,
                       Float weigth, Float height, String avatar) {
        this.user_id = user_id;
        this.city = city;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.adress = adress;
        this.menstrual_cycle = menstrual_cycle;
        this.red_days = red_days;
        this.last_menstruation_start_day = last_menstruation_start_day;
        this.weigth = weigth;
        this.height = height;
        this.avatar = avatar;
    }
}
