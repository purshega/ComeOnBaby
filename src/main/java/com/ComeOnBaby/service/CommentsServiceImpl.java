package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.CommentsDao;
import com.ComeOnBaby.model.Comment;
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
    public void addNewComments(Comment comment) {
        commentsDao.create(comment);
    }

    @Override
    @Transactional
    public void updateComments(Comment comment) {
        commentsDao.update(comment);
    }

    @Override
    @Transactional
    public void deleteComments(Comment comment) {
        commentsDao.delete(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllComments() {
        return commentsDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findByBlogID(Long blogID) {return commentsDao.findByBlogID(blogID);}
}
