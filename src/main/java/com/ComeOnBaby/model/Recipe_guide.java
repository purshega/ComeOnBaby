package com.ComeOnBaby.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "recipe_guide")

public class Recipe_guide {

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

    @Column(name="image_thumbnail")
    private String image_thumbnail;

    @Column(name="url_naver")
    private String url_naver;


    public Recipe_guide(){

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

    public String getDateFormat() {
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage_thumbnail() {
        return image_thumbnail;
    }

    public void setImage_thumbnail(String image_thumbnail) {
        this.image_thumbnail = image_thumbnail;
    }

    public String getUrl_naver() {
        return url_naver;
    }

    public void setUrl_naver(String url_naver) {
        this.url_naver = url_naver;
    }

    public Recipe_guide(Long id, String title, Date date, String image_thumbnail, String url_naver) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.image_thumbnail = image_thumbnail;
        this.url_naver = url_naver;
    }


}
