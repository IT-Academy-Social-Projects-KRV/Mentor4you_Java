package com.mentor4you.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Document
public class Complaint {

private String complainId;
private String targetTd;
private int senderId;
private String message;
@Enumerated(EnumType.STRING)
private TypeComplain typeComplain;
private boolean showStatus;
private LocalDateTime timestamp;

    public Complaint() {
    }


}
