package com.ComeOnBaby.service;


import com.ComeOnBaby.model.Images;

import java.util.List;

public interface ImagesService {
    void addNewImages(Images images);
    void updateImages(Images images);
    void deleteImages(Images images);
    List<Images> getAllImages();
}
