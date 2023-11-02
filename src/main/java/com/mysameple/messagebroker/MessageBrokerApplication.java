package com.mysameple.messagebroker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.jackson.ModelObjectMapper;
import com.linecorp.bot.messaging.model.TextMessage;
import com.linecorp.bot.spring.boot.handler.annotation.EventMapping;
import com.linecorp.bot.spring.boot.handler.annotation.LineMessageHandler;
import com.linecorp.bot.webhook.model.Event;
import com.linecorp.bot.webhook.model.MessageContent;
import com.linecorp.bot.webhook.model.MessageEvent;
import com.linecorp.bot.webhook.model.TextMessageContent;
import com.mysameple.messagebroker.dao.impl.MessageHistoryDaoImpl;
import com.mysameple.messagebroker.model.MessageHistory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@LineMessageHandler
public class MessageBrokerApplication {
    private static final ObjectMapper mapper = ModelObjectMapper.createNewObjectMapper();
    private final Logger logger = LogManager.getLogger(getClass());
    private final MessageHistoryDaoImpl messageHistoryDao;

    public MessageBrokerApplication(MessageHistoryDaoImpl messageHistoryDao) {
        this.messageHistoryDao = messageHistoryDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageBrokerApplication.class, args);
    }

    @EventMapping
    public TextMessage handleTextMessageEvent(Event event) {
        MessageHistory messageHistory = event2MessageHistory(event);
        messageHistoryDao.save(messageHistory);
        return new TextMessage(messageHistory.getMessage());
    }

    private MessageHistory event2MessageHistory(Event event) {
        String userId = event.source().userId();

        MessageEvent messageEvent = mapper.convertValue(event, MessageEvent.class);
        MessageContent messageContent = messageEvent.message();
        TextMessageContent textMessageContent = mapper.convertValue(messageContent, TextMessageContent.class);
        String userMessage = textMessageContent.text();
        logger.info("userId:" + userId + ", message:" + userMessage);
        return new MessageHistory(userId, userMessage);
    }
}
