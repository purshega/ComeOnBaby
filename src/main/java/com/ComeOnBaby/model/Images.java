package com.ComeOnBaby.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class Images {

    @Id
    @NotEmpty
    @Column(name="id", nullable=false)
    private Long id;

    @NotEmpty
    @Column(name="type", unique=true, nullable=false)
    private String type = ImageType.BLOG.getImageType();

    @Column(name="images_0")
    private String images_0;

    @Column(name="images_1")
    private String images_1;

    @Column(name="images_2")
    private String images_2;

    @Column(name="images_3")
    private String images_3;

    @Column(name="images_4")
    private String images_4;

    @Column(name="images_5")
    private String images_5;

    @Column(name="images_6")
    private String images_6;

    @Column(name="images_7")
    private String images_7;

    @Column(name="images_8")
    private String images_8;

    @Column(name="images_9")
    private String images_9;

    public Images(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImages_0() {
        return images_0;
    }

    public void setImages_0(String images_0) {
        this.images_0 = images_0;
    }

    public String getImages_1() {
        return images_1;
    }

    public void setImages_1(String images_1) {
        this.images_1 = images_1;
    }

    public String getImages_2() {
        return images_2;
    }

    public void setImages_2(String images_2) {
        this.images_2 = images_2;
    }

    public String getImages_3() {
        return images_3;
    }

    public void setImages_3(String images_3) {
        this.images_3 = images_3;
    }

    public String getImages_4() {
        return images_4;
    }

    public void setImages_4(String images_4) {
        this.images_4 = images_4;
    }

    public String getImages_5() {
        return images_5;
    }

    public void setImages_5(String images_5) {
        this.images_5 = images_5;
    }

    public String getImages_6() {
        return images_6;
    }

    public void setImages_6(String images_6) {
        this.images_6 = images_6;
    }

    public String getImages_7() {
        return images_7;
    }

    public void setImages_7(String images_7) {
        this.images_7 = images_7;
    }

    public String getImages_8() {
        return images_8;
    }

    public void setImages_8(String images_8) {
        this.images_8 = images_8;
    }

    public String getImages_9() {
        return images_9;
    }

    public void setImages_9(String images_9) {
        this.images_9 = images_9;
    }

    public Images(Long id, String type, String images_0, String images_1, String images_2, String images_3, String images_4,
                  String images_5, String images_6, String images_7, String images_8, String images_9) {
        this.id = id;
        this.type = type;
        this.images_0 = images_0;
        this.images_1 = images_1;
        this.images_2 = images_2;
        this.images_3 = images_3;
        this.images_4 = images_4;
        this.images_5 = images_5;
        this.images_6 = images_6;
        this.images_7 = images_7;
        this.images_8 = images_8;
        this.images_9 = images_9;
    }
}
