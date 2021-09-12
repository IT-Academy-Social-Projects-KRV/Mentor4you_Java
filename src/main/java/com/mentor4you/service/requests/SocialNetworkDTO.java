package com.mentor4you.service.requests;

import com.mentor4you.model.Accounts;
import com.mentor4you.model.Social_networks;



public class SocialNetworkDTO {

    private Social_networks social_networks;


    private String url;

    public SocialNetworkDTO() {
    }

    public SocialNetworkDTO(Social_networks social_networks, String url) {
        this.social_networks = social_networks;
        this.url = url;
    }

    public Social_networks getSocial_networks() {
        return social_networks;
    }

    public void setSocial_networks(Social_networks social_networks) {
        this.social_networks = social_networks;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SocialNetworkDTO{" +
                "social_networks=" + social_networks +
                ", url='" + url + '\'' +
                '}';
    }
}
