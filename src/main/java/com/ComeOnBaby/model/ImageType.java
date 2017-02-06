package com.ComeOnBaby.model;


import java.io.Serializable;

public enum ImageType implements Serializable {
    NOTICE("notice"),
    BLOG("blog");

    String imageType;

    private ImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageType() {
        return imageType;
    }


}
