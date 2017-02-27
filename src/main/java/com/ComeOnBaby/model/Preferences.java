package com.ComeOnBaby.model;

import org.hibernate.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "preferences")

public class Preferences {

//    @OneToOne(cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private AppUser user;

    @Id
    @Column(name="user_id", nullable=false)
    private Long id;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_id")
    private AppUser appUser;

    @Column(name="city_id")
    private Long city;

    @Column(name="nickname")
    private String nickname;

    @Column(name="gender")
    private String gender;

    @Column(name="birthday")
    private Integer birth_year;

    @Column(name="adress")
    private String address;

    @Column(name="menstrual_cycle")
    private Integer menstrual_cycle;

    @Column(name="red_days")
    private Integer red_days;

    @Column(name="last_menstruation_start_day")
    private String last_cycle;

    @Column(name="weigth")
    private Float weight;

    @Column(name="height")
    private Float height;

    @Column(name="avatar")
    private String avatar;

    @Column(name="is_agreement")
    private Boolean is_agreement;

    @Column(name="is_finish_question")
    private Boolean is_finish_question;

    public Preferences(){
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
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

    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getLast_cycle() {
        return last_cycle;
    }

    public void setLast_cycle(String last_cycle) {
        this.last_cycle = last_cycle;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
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

    public Boolean getIs_agreement() {
        return is_agreement;
    }

    public void setIs_agreement(Boolean is_agreement) {
        this.is_agreement = is_agreement;
    }

    public Boolean getIs_finish_question() {
        return is_finish_question;
    }

    public void setIs_finish_question(Boolean is_finish_question) {
        this.is_finish_question = is_finish_question;
    }

    public Preferences(Long id, Long city, String nickname, String gender, Integer birth_year, String address, Integer menstrual_cycle, Integer red_days, String last_cycle, Float weight, Float height, String avatar, Boolean is_agreement, Boolean is_finish_question) {
        this.id = id;
        this.city = city;
        this.nickname = nickname;
        this.gender = gender;
        this.birth_year = birth_year;
        this.address = address;
        this.menstrual_cycle = menstrual_cycle;
        this.red_days = red_days;
        this.last_cycle = last_cycle;
        this.weight = weight;
        this.height = height;
        this.avatar = avatar;
        this.is_agreement = is_agreement;
        this.is_finish_question = is_finish_question;
    }

    @Override
    public String toString() {
        return "Preferences{" +
                "id=" + id +
                ", city=" + city +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", birth_year=" + birth_year +
                ", address='" + address + '\'' +
                ", menstrual_cycle=" + menstrual_cycle +
                ", red_days=" + red_days +
                ", last_cycle='" + last_cycle + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", avatar='" + avatar + '\'' +
                ", is_agreement=" + is_agreement +
                ", is_finish_question=" + is_finish_question +
                '}';
    }
}
