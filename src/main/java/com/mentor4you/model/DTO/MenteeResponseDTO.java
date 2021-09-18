package com.mentor4you.model.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class MenteeResponseDTO {

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private Map<String, String> socialMap;

    public MenteeResponseDTO() {
    }


    public MenteeResponseDTO(String firstName,
                             String lastName,
                             Map<String, String> socialMap) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialMap = socialMap;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<String, String> getSocialMap() {
        return socialMap;
    }

    public void setSocialMap(Map<String, String> socialMap) {
        this.socialMap = socialMap;
    }
}
