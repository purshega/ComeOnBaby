package com.ComeOnBaby.service;

import com.ComeOnBaby.model.ImgText;
import com.ComeOnBaby.model.Notice;

import java.util.List;

/**
 * Created by Макс on 24.02.2017.
 */
public interface ImgTextService {
    void addNew(ImgText obj);
    void update(ImgText obj);
    void delete(ImgText obj);
    List<ImgText> getAllImgTextByNotice(Notice notice);
}
