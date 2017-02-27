package com.ComeOnBaby.service;


import com.ComeOnBaby.model.Notice;

import java.util.List;

public interface NoticeService {
    void addNewNotice(Notice notice);
    void updateNotice(Notice notice);
    Notice get(Long id);
    void deleteNotice(Notice notice);
    List<Notice> getAllNotice();
}
