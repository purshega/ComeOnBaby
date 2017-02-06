package com.ComeOnBaby.model;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class Users {

    public Users(){
    }

    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Column(name="email", nullable=false)
    private String email;


    @Column(name="password", nullable=false)
    private String password;

    @Column(name="kakao", nullable=false)
    private String kakao;

    @Column(name="facebook", nullable=false)
    private String facebook;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKakao() {
        return kakao;
    }

    public void setKakao(String kakao) {
        this.kakao = kakao;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public Users(String email, String password, String kakao, String facebook) {
        this.email = email;
        this.password = password;
        this.kakao = kakao;
        this.facebook = facebook;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user: " + id + '\'' +
                ", email: " + email + '\'' +
                ", password: " + password + '\'' +
                ", kako: " + kakao + '\'' +
                ", facebook: " + facebook +
                '}';
    }
}
