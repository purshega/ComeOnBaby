package com.ComeOnBaby.dao;



import com.ComeOnBaby.model.Notice;

import java.util.List;

/**
 * Created by adm on 1/30/2017.
 */
public interface NoticeDao {
    Long create(Notice notice);
    Notice read(Long id);
    void update(Notice notice);
    void delete(Notice notice);
    List<Notice> findAll();
}
