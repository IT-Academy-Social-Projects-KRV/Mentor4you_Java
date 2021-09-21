package com.mentor4you.model.DTO;

public class AvatarUpdateRequest {
    private String avatarURL;
    private String token;

    public AvatarUpdateRequest(String avatarURL, String token) {
        this.avatarURL = avatarURL;
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatarURL() {
        return avatarURL;
    }
    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
