package com.ComeOnBaby.dao;



import com.ComeOnBaby.model.Likes;

import java.util.List;

/**
 * Created by adm on 1/30/2017.
 */
public interface LikesDao {
    Long create(Likes likes);
    Likes read(Long id);
    void update(Likes likes);
    void delete(Likes likes);
    List<Likes> findAll();
}
