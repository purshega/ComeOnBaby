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
    private Long socialId;

    @Column(name="login_type", nullable=false)
    private Integer loginType;

    public AppUser(){
    }

    public AppUser(String email, String password, Long socialId, Integer loginType) {
        this.email = email;
        this.password = password;
        this.socialId = socialId;
        this.loginType = loginType;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public Long getSocialId() {return socialId;}
    public void setSocialId(Long socialId) {this.socialId = socialId;}

    public Integer getLoginType() {return loginType;}
    public void setLoginType(Integer loginType) {this.loginType = loginType;}

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", socialId=" + socialId +
                ", loginType=" + loginType +
                '}';
    }
}
