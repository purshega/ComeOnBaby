package com.ComeOnBaby.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "q_a")
public class Q_A {

    @Id
    @NotEmpty
    @Column(name="id", nullable=false)
    private Long id;

    @NotEmpty
    @Column(name="id_user", nullable=false)
    private Long id_user;

    @NotEmpty
    @Column(name="date", nullable=false)
    private Date date;

    @NotEmpty
    @Column(name="title", nullable=false)
    private String title;

    @NotEmpty
    @Column(name="text", nullable=false)
    private String text;

    @NotEmpty
    @Column(name="is_access", nullable=false)
    private Boolean is_access;

    @NotEmpty
    @Column(name="answer", nullable=false)
    private String answer;


    public Q_A(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIs_access() {
        return is_access;
    }

    public void setIs_access(Boolean is_access) {
        this.is_access = is_access;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Q_A(Long id, Long id_user, Date date, String title, String text, Boolean is_access, String answer) {
        this.id = id;
        this.id_user = id_user;
        this.date = date;
        this.title = title;
        this.text = text;
        this.is_access = is_access;
        this.answer = answer;
    }

}
