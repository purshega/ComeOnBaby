package com.ComeOnBaby.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "q_a")
public class Q_A {

    @Id
    @NotEmpty
    @Column(name="id", nullable=false)
    private Long id;


    @Column(name="id_user", nullable=false)
    private Long id_user;


    @Column(name="question_date", nullable=false)
    private Date question_date;


    @Column(name="answer_date", nullable=false)
    private Date answer_date;


    @Column(name="title", nullable=false)
    private String title;


    @Column(name="text", nullable=false)
    private String text;


    @Column(name="is_access", nullable=false)
    private Boolean is_access;


    @Column(name="answer", nullable=false)
    private String answer;

    @Column(name="is_answer", nullable=false)
    private Boolean is_answer;


    public Q_A(){}

    public Boolean getIs_answer() {
        return is_answer;
    }

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

    public Date getQuestion_date() {
        return question_date;
    }

    public String getQuestionDateFormat() {
        return  new SimpleDateFormat("yyyy-MM-dd").format(question_date);
    }

    public void setQuestion_date(Date question_date) {
        this.question_date = question_date;
    }

    public Date getAnswer_date() {
        return answer_date;
    }

    public String getAnswerDateFormat() {
        if (answer_date!=null){
            return  new SimpleDateFormat("yyyy-MM-dd").format(answer_date);
        } else {
            return "";
        }
    }

    public void setAnswer_date(Date answer_date) {
        this.answer_date = answer_date;
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

    public Q_A(Long id, Long id_user, Date question_date, Date answer_date, String title, String text, Boolean is_access, String answer) {
        this.id = id;
        this.id_user = id_user;
        this.question_date = question_date;
        this.answer_date = answer_date;
        this.title = title;
        this.text = text;
        this.is_access = is_access;
        this.answer = answer;
    }
}
