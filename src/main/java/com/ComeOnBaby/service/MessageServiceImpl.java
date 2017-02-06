package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("messageService")
public class MessageServiceImpl implements MessageService {

    private MessageDao messageDao;

    @Autowired(required=true)
    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    @Transactional
    public void addNewMessage(Message message) { messageDao.create(message);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Message> getAllMessages() {
        return messageDao.findAll();
    }

    @Override
    @Transactional
    public void updateMessage(Message message) {
        messageDao.update(message);
    }

    @Override
    @Transactional
    public void deleteMessage(Message message) {
        messageDao.delete(message);
    }

}
