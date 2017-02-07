package com.ComeOnBaby.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "notice")
public class Notice {

    @Id
    @NotEmpty
    @Column(name="id", nullable=false)
    private Long id;

    @NotEmpty
    @Column(name="date", nullable=false)
    private Date date;

    @NotEmpty
    @Column(name="title", nullable=false)
    private String title;

    @NotEmpty
    @Column(name="text", nullable=false)
    private String text;

    public Notice(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Notice(Long id, Date date, String title, String text) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.text = text;
    }
}
