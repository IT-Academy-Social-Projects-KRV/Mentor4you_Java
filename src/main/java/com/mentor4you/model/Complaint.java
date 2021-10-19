package com.mentor4you.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Document
public class Complaint {

private int complainId;
private int reviewTd;
private int senderId;
private int mentorId;
private String message;
@Enumerated(EnumType.STRING)
private TypeComplain typeComplain;
private boolean showStatus;
private LocalDateTime timestamp;

    public Complaint() {
    }

    public Complaint(int complainId,
                     int reviewTd,
                     int senderId,
                     int mentorId,
                     String message,
                     TypeComplain typeComplain,
                     boolean showStatus,
                     LocalDateTime timestamp) {
        this.complainId = complainId;
        this.reviewTd = reviewTd;
        this.senderId = senderId;
        this.mentorId = mentorId;
        this.message = message;
        this.typeComplain = typeComplain;
        this.showStatus = showStatus;
        this.timestamp = timestamp;
    }

    public int getComplainId() {
        return complainId;
    }

    public void setComplainId(int complainId) {
        this.complainId = complainId;
    }

    public int getReviewTd() {
        return reviewTd;
    }

    public void setReviewTd(int reviewTd) {
        this.reviewTd = reviewTd;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TypeComplain getTypeComplain() {
        return typeComplain;
    }

    public void setTypeComplain(TypeComplain typeComplain) {
        this.typeComplain = typeComplain;
    }

    public boolean isShowStatus() {
        return showStatus;
    }

    public void setShowStatus(boolean showStatus) {
        this.showStatus = showStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
