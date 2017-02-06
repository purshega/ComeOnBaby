package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.ImagesDao;
import com.ComeOnBaby.model.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("imagesService")
public class ImagesServiceImpl implements ImagesService  {

    private ImagesDao imagesDao;

    @Autowired(required = true)
    public void setCaseDao(ImagesDao imagesDao) {
        this.imagesDao = imagesDao;
    }

    @Override
    @Transactional
    public void addNewImages(Images images) {
        imagesDao.create(images);
    }

    @Override
    @Transactional
    public void updateImages(Images images) {
        imagesDao.update(images);
    }

    @Override
    @Transactional
    public void deleteImages(Images images) {
        imagesDao.delete(images);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Images> getAllImages() {
        return imagesDao.findAll();
    }
}

    
    

