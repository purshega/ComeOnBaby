package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.CommentsDao;
import com.ComeOnBaby.model.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {

    private CommentsDao commentsDao;

    @Autowired(required = true)
    public void setCaseDao(CommentsDao commentsDao) {
        this.commentsDao = commentsDao;
    }

    @Override
    @Transactional
    public void addNewComments(Comments comments) {
        commentsDao.create(comments);
    }

    @Override
    @Transactional
    public void updateComments(Comments comments) {
        commentsDao.update(comments);
    }

    @Override
    @Transactional
    public void deleteComments(Comments comments) {
        commentsDao.delete(comments);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comments> getAllComments() {
        return commentsDao.findAll();
    }
}
