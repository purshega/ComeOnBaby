package com.ComeOnBaby.model;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name="email", nullable = true)
    private String email;

    @Column(name="password", nullable=true)
    private String password;

    @Column(name="social_id", nullable=true)
    private Long socialID;

    @Column(name="login_type", nullable=false)
    private String loginType;

    @OneToOne(mappedBy = "appUser", fetch = FetchType.EAGER)
    private Preferences preferences;

    public AppUser(){
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public AppUser(String email, String password, Long socialID, String loginType) {
        this.email = email;
        this.password = password;
        this.socialID = socialID;
        this.loginType = loginType;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public Long getSocialId() {return socialID;}
    public void setSocialId(Long socialId) {this.socialID = socialId;}

    public String getLoginType() {return loginType;}
    public void setLoginType(String loginType) {this.loginType = loginType;}

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", email=" + email +
                ", password=" + password +
                ", socialID=" + socialID +
                ", loginType=" + loginType + '}';
    }
    public String getStringToTable(){
        StringBuilder sb = new StringBuilder();

        sb.append("<tr>");
        sb.append("<td> id </td>");
        sb.append("<td> <a href=\"../cabinet/user/"+id+"\">"+email+"</a> </td>");
        if(preferences!=null) {
            sb.append("<td>"+preferences.getNickname()+"</td>");//nickname
            sb.append("<td>"+preferences.getBirth_year()+"</td>");//bithyear
        } else {
            sb.append("<td> </td>");//nickname
            sb.append("<td> </td>");//bithyear
        }
        sb.append("<td> "+getLoginType()+" </td>");
        if(preferences!=null) {
            sb.append("<td>"+preferences.getGender()+"</td>");//gender
        } else {
            sb.append("<td> </td>");//gender
        }
        sb.append("</tr>");

        return sb.toString();
    }
}
