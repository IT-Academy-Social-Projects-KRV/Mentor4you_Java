package com.mentor4you.service.requests;

import com.mentor4you.model.Languages;


import java.util.List;


public class UpdateAccountRequest {
    private String phoneNumber;


    private List<Languages> languagesList;

    private UpdateUserRequest updateUserRequest;



    public UpdateAccountRequest(String phoneNumber,List<Languages> languagesList) {
        this.phoneNumber = phoneNumber;

        this.languagesList = languagesList;

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
