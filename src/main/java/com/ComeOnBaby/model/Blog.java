package com.ComeOnBaby.model;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="id_user", nullable=false)
    private Long id_user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_user",insertable = false, updatable = false)
    private AppUser appUser;

    @Column(name="type", nullable=false)
    private Integer type;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="text", nullable=false)
    private String text;

    @Column(name="datetime", nullable=false)
    private Date datetime;

    @Column(name="images")
    private String images;

    public Blog(){

    }

    public Blog(Long id, Long id_user, Integer type, String title, String text, Date datetime, String images) {
        this.id = id;
        this.id_user = id_user;
        this.type = type;
        this.title = title;
        this.text = text;
        this.datetime = datetime;
        this.images = images;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Long getId_user() {return id_user;}
    public void setId_user(Long id_user) {this.id_user = id_user;}
    public Integer getType() {return type;}
    public void setType(Integer type) {this.type = type;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
    public Date getDatetime() {return datetime;}
    public void setDatetime(Date datetime) {this.datetime = datetime;}
    public String getImages() {return images;}
    public void setImages(String images) {this.images = images;}

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getStringToTable(){
        SimpleDateFormat formatDate = new SimpleDateFormat("MM.dd.yyyy");
        String strDate = formatDate.format(datetime);

        StringBuilder sb = new StringBuilder();

        sb.append("<tr>");
        sb.append("<td> "+id+" </td>");//id
        if(appUser!=null) {
            sb.append("<td> "+appUser.getEmail()+" </td>");//email
        } else {
            sb.append("<td> </td>");//email
        }
        if(appUser!=null && appUser.getPreferences()!=null) {
            sb.append("<td> "+appUser.getPreferences().getNickname()+" </td>");//nickname
        } else {
            sb.append("<td> </td>");//nickname
        }
        sb.append("<td> "+strDate+"</td>");//date
        sb.append("<td> "+title+" </td>");//title
        sb.append("<td> "+text+"</td>");//text
        sb.append("</tr>");

        return sb.toString();
    }
}
