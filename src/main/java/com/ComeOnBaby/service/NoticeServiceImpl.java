package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.NoticeDao;
import com.ComeOnBaby.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
    private NoticeDao noticeDao;

    @Autowired(required = true)
    public void setCaseDao(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    @Override
    @Transactional
    public void addNewNotice(Notice notice) {
        noticeDao.create(notice);
    }

    @Override
    @Transactional
    public void updateNotice(Notice notice) {
        noticeDao.update(notice);
    }

    @Override
    @Transactional
    public Notice get(Long id) {
        return noticeDao.read(id);
    }

    @Override
    @Transactional
    public void deleteNotice(Notice notice) {
        noticeDao.delete(notice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Notice> getAllNotice() {
        return noticeDao.findAll();
    }
}

