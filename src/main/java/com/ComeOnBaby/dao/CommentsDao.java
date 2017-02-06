package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.Comments;

import java.util.List;

public interface CommentsDao {
    Long create(Comments comments);
    Comments read(Long id);
    void update(Comments comments);
    void delete(Comments comments);
    List<Comments> findAll();
}
