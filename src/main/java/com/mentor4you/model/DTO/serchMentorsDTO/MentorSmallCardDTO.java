package com.mentor4you.model.DTO.serchMentorsDTO;

import java.util.List;

public class MentorSmallCardDTO {
    int id;
    String firstName;
    String lastName;
    String avatar;
    String foundCategory;
    int rate;
    List<String> categories;

    public MentorSmallCardDTO() {
    }

    public MentorSmallCardDTO(int id,
                              String firstName,
                              String lastName,
                              String avatar,
                              String foundCategory,
                              int rate, List<String> categories) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.foundCategory = foundCategory;
        this.rate = rate;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFoundCategory() {
        return foundCategory;
    }

    public void setFoundCategory(String foundCategory) {
        this.foundCategory = foundCategory;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
