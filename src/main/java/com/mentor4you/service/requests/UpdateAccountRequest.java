package com.mentor4you.service.requests;

import com.mentor4you.model.Languages;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class UpdateAccountRequest {
    private String phoneNumber;


    private List<Languages> languagesList;

    private UpdateUserRequest updateUserRequest;



    public UpdateAccountRequest() {
    }

    public UpdateAccountRequest(String phoneNumber, List<Languages> languagesList, UpdateUserRequest updateUserRequest) {
        this.phoneNumber = phoneNumber;
        this.languagesList = languagesList;
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

}
