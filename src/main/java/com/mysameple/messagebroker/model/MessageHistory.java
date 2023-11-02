package com.mysameple.messagebroker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageHistory {
    @Id
    private String id;
    private String userId;
    private String message;

    public MessageHistory(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }
}
