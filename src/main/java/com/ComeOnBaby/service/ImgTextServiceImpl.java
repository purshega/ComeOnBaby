package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.ImgTextDao;
import com.ComeOnBaby.model.ImgText;
import com.ComeOnBaby.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Макс on 24.02.2017.
 */
@Service("imgTextService")
public class ImgTextServiceImpl implements ImgTextService {

    private ImgTextDao imgTextDao;

    @Autowired(required = true)
    public void setImtTextDao(ImgTextDao imtTextDao) {
        this.imgTextDao = imgTextDao;
    }

    @Override
    @Transactional
    public void addNew(ImgText obj) {
        imgTextDao.create(obj);
    }

    @Override
    @Transactional
    public void update(ImgText obj) {
        imgTextDao.update(obj);
    }

    @Override
    @Transactional
    public void delete(ImgText obj) {
        imgTextDao.delete(obj);
    }

    @Override
    @Transactional
    public List<ImgText> getAllImgTextByNotice(Notice notice) {
        return imgTextDao.findAllByNotice(notice);
    }
}
