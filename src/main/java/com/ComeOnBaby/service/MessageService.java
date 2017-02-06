package com.ComeOnBaby.service;




import com.ComeOnBaby.model.Message;

import java.util.List;

public interface MessageService {
    void addNewMessage(Message message);
    void updateMessage(Message message);
    void deleteMessage(Message message);
    List<Message> getAllMessages();
}
