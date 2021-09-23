package com.mentor4you.model.DTO;

import java.io.IOException;

public class AvatarUpdateRequest {
    private String avatarURL;

    public AvatarUpdateRequest(String avatarURL) throws IOException {
        if (!avatarURL.startsWith("http://")){
            throw new IOException ("Invalid avatar URL");
        }
        this.avatarURL = avatarURL;
    }

    public String getAvatarURL() {
        return avatarURL;
    }
    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
