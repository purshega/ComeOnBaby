package com.ComeOnBaby.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name="id_blog", nullable=false)
    private Long id_blog;

    @Column(name="id_user", nullable=false)
    private Long id_user;

    @Column(name="text", nullable=false)
    private String text;

    @Column(name="datetime", nullable=false)
    private Date datetime;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Long getId_blog() {
        return id_blog;
    }
    public void setId_blog(Long id_blog) {
        this.id_blog = id_blog;
    }
    public Long getId_user() {
        return id_user;
    }
    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Date getDatetime() {
        return datetime;
    }
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Comment(Long id_blog, Long id_user, String text, Date datetime) {
        this.id_blog = id_blog;
        this.id_user = id_user;
        this.text = text;
        this.datetime = datetime;
    }

    public Comment() {
    }
}

