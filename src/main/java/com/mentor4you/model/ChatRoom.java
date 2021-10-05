package com.mentor4you.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
public class ChatRoom {

    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
    private String recipientName;
    private String recipientAvatar;

    public ChatRoom(String chatId, String senderId, String recipientId, String recipientName, String recipientAvatar) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.recipientName = recipientName;
        this.recipientAvatar = recipientAvatar;
    }

    public ChatRoom() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientAvatar() {
        return recipientAvatar;
    }

    public void setRecipientAvatar(String recipientAvatar) {
        this.recipientAvatar = recipientAvatar;
    }
}
