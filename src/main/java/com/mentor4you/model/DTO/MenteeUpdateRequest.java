package com.mentor4you.model.DTO;

import java.util.Map;

public class MenteeUpdateRequest {
/*
    ObjectMapper mapper = new ObjectMapper();
    Map<String,Object> map = mapper.readValue(json, Map.class);
    */

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private Map<String, String> socialMap;

    public MenteeUpdateRequest() {
    }

    public MenteeUpdateRequest(int id, String firstName,
                               String lastName,
                               String email,
                               String phoneNumber,
                               Map<String, String> socialMap) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.socialMap = socialMap;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Map<String, String> getSocialMap() {
        return socialMap;
    }
}
