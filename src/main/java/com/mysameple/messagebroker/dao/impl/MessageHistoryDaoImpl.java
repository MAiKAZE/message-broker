package com.mysameple.messagebroker.dao.impl;

import com.mysameple.messagebroker.dao.MessageHistoryDao;
import com.mysameple.messagebroker.model.MessageHistory;
import com.mysameple.messagebroker.model.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageHistoryDaoImpl implements MessageHistoryDao {

    private final MongoTemplate mongoTemplate;

    public MessageHistoryDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<MessageHistory> findAll() {
        return mongoTemplate.findAll(MessageHistory.class);
    }

    @Override
    public MessageHistory save(MessageHistory messageHistory) {
        return mongoTemplate.save(messageHistory);
    }

    @Override
    public User listUserId() {
        List<String> result = mongoTemplate.findDistinct(new Query(), "userId", MessageHistory.class, String.class);
        return new User(result);
    }

    @Override
    public List<MessageHistory> listMessagesByUser(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, MessageHistory.class);
    }
}
