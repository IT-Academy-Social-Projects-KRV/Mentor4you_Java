package com.mentor4you.service.requests;

import com.mentor4you.model.Languages;
import com.mentor4you.model.Links_to_accounts;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class UpdateAccountRequest {
    private String phoneNumber;


    private List<Languages> languagesList;

    private Set<SocialNetworkDTO> socialNetworks;

    private UpdateUserRequest updateUserRequest;



    public UpdateAccountRequest() {
    }

    public UpdateAccountRequest(String phoneNumber, List<Languages> languagesList, Set<Links_to_accounts> socialNetworks, UpdateUserRequest updateUserRequest) {
        this.phoneNumber = phoneNumber;
        this.languagesList = languagesList;
        this.socialNetworks = new HashSet<>();
        if (socialNetworks!=null)
            {
                for (Links_to_accounts ns : socialNetworks)
                    this.socialNetworks.add(new SocialNetworkDTO(ns.getSocial_networks(), ns.getUrl()));
            }
        else  this.socialNetworks.add(new SocialNetworkDTO());
        this.updateUserRequest = updateUserRequest;

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public List<Languages> getLanguagesList() {
        return languagesList;
    }

    public void setLanguagesList(List<Languages> languagesList) {
        this.languagesList = languagesList;
    }

    public UpdateUserRequest getUpdateUserRequest() {
        return updateUserRequest;
    }

    public void setUpdateUserRequest(UpdateUserRequest updateUserRequest) {
        this.updateUserRequest = updateUserRequest;
    }

    public Set<SocialNetworkDTO> getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(Set<SocialNetworkDTO> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }
}
