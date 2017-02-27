package com.ComeOnBaby.dao;

import com.ComeOnBaby.model.City;
import com.ComeOnBaby.model.ImgText;
import com.ComeOnBaby.model.Notice;

import java.util.List;

/**
 * Created by Макс on 24.02.2017.
 */
public interface ImgTextDao {
    Long create(ImgText obj);
    ImgText read(Long id);
    void update(ImgText obj);
    void delete(ImgText obj);
    List<ImgText> findAllByNotice(Notice notice);
}
