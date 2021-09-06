package com.mentor4you.service.requests;

public class UpdateUserRequest {

    private String first_name;
    private String last_name;
    private String avatar;

    public UpdateUserRequest(String first_name, String last_name, String avatar) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
