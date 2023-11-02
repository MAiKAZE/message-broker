package com.mysameple.messagebroker.dao;

import com.mysameple.messagebroker.model.MessageHistory;
import com.mysameple.messagebroker.model.User;

import java.util.List;

public interface MessageHistoryDao {
    public List<MessageHistory> findAll();

    public MessageHistory save(MessageHistory messageHistory);

    public User listUserId();

    public List<MessageHistory> listMessagesByUser(String userId);
}
