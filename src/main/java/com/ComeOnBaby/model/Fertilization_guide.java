package com.ComeOnBaby.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "fertilization_guide")
public class Fertilization_guide {

    @Id
    @NotEmpty
    @Column(name="id", nullable=false)
    private Long id;

    @NotEmpty
    @Column(name="title", nullable=false)
    private String title;

    @NotEmpty
    @Column(name="date", nullable=false)
    private Date date;

    @Column(name="image")
    private String image;

    public Fertilization_guide(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateFormat() {
        return  new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Fertilization_guide(Long id, String title, Date date, String image) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Fertilization_guide{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", image='" + image + '\'' +
                '}';
    }
}
