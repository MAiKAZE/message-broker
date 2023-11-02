package com.mysameple.messagebroker.controller;

import com.linecorp.bot.messaging.client.MessagingApiClient;
import com.linecorp.bot.messaging.model.BroadcastRequest;
import com.linecorp.bot.messaging.model.Message;
import com.linecorp.bot.messaging.model.TextMessage;
import com.mysameple.messagebroker.dao.impl.MessageHistoryDaoImpl;
import com.mysameple.messagebroker.model.MessageHistory;
import com.mysameple.messagebroker.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class MainController {

    private final MessageHistoryDaoImpl messageHistoryDao;
    private final MessagingApiClient client;

    public MainController(MessageHistoryDaoImpl messageHistoryDao, MessagingApiClient client) {
        this.messageHistoryDao = messageHistoryDao;
        this.client = client;
    }

    @PostMapping("/sendMessage")
    public TextMessage sendMessage(@RequestBody String message) {
        List<Message> messages = new ArrayList<>();
        messages.add(new TextMessage(message));
        client.broadcast(UUID.randomUUID(), new BroadcastRequest(messages, false));
        return new TextMessage(message);
    }

    @GetMapping("/list/users")
    public User listUsers() {
        return messageHistoryDao.listUserId();
    }

    @GetMapping("/list/historyMessage")
    public List<MessageHistory> listHistoryMessage() {
        return messageHistoryDao.findAll();
    }

    @GetMapping("/message/{userId}")
    public List<MessageHistory> findHistoryMessagesByUser(@PathVariable String userId) {
        return messageHistoryDao.listMessagesByUser(userId);
    }
}
