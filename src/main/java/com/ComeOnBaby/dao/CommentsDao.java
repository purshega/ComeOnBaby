package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.Comment;

import java.util.List;

public interface CommentsDao {
    Long create(Comment comment);
    Comment read(Long id);
    void update(Comment comment);
    void delete(Comment comment);
    List<Comment> findAll();
    public List<Comment> findByBlogID(Long blogID);
}
