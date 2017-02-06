package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.LikesDao;
import com.ComeOnBaby.model.Likes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("likesService")
public class LikesServiceImpl implements LikesService{
    private LikesDao likesDao;

    @Autowired(required = true)
    public void setCaseDao(LikesDao likesDao) {
        this.likesDao = likesDao;
    }

    @Override
    @Transactional
    public void addNewLikes(Likes likes) {
        likesDao.create(likes);
    }

    @Override
    @Transactional
    public void updateLikes(Likes likes) {
        likesDao.update(likes);
    }

    @Override
    @Transactional
    public void deleteLikes(Likes likes) {
        likesDao.delete(likes);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Likes> getAllLikes() {
        return likesDao.findAll();
    }
}

    

