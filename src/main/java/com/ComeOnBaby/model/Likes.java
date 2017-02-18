package com.ComeOnBaby.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="likes")
public class Likes {

    @Id
    @NotEmpty
    @Column(name="id_blog", nullable=false)
    private Long id_blog;

    @NotEmpty
    @Column(name="id_user", nullable=false)
    private Long id_user;

    @NotEmpty
    @Column(name="like", nullable=false)
    private Boolean like;

    public Likes(){

    }

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

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    public Likes(Long id_blog, Long id_user, Boolean like) {
        this.id_blog = id_blog;
        this.id_user = id_user;
        this.like = like;
    }

    @Override
    public String toString() {
        return "Likes{" +
                "id_blog=" + id_blog +
                ", id_user=" + id_user +
                ", like=" + like +
                '}';
    }
}
