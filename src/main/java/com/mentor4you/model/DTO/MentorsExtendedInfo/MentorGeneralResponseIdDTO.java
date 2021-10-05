package com.mentor4you.model.DTO.MentorsExtendedInfo;


import com.mentor4you.model.Certificats;
import com.mentor4you.model.DTO.MenteeResponseDTO;
import com.mentor4you.model.Educations;
import com.mentor4you.model.GroupServ;
import com.mentor4you.model.Mentors_to_categories;

import java.util.List;
import java.util.Set;

public class MentorGeneralResponseIdDTO {

    private int id;

    private MenteeResponseDTO accountInfo;

    private String description;
    private boolean showable_status;
    private boolean isOnline;
    private boolean isOfflineIn;
    private boolean isOfflineOut;

    private GroupServ groupServ;

    private int rating;

    private List<Educations> educations;

    private List<Certificats> certificats;

    private Set<Mentors_to_categories> categories;

    private Set<String> languages;


    public MentorGeneralResponseIdDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isShowable_status() {
        return showable_status;
    }

    public void setShowable_status(boolean showable_status) {
        this.showable_status = showable_status;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isOfflineIn() {
        return isOfflineIn;
    }

    public void setOfflineIn(boolean offlineIn) {
        isOfflineIn = offlineIn;
    }

    public boolean isOfflineOut() {
        return isOfflineOut;
    }

    public void setOfflineOut(boolean offlineOut) {
        isOfflineOut = offlineOut;
    }

    public List<Educations> getEducations() {
        return educations;
    }

    public void setEducations(List<Educations> educations) {
        this.educations = educations;
    }

    public List<Certificats> getCertificats() {
        return certificats;
    }

    public void setCertificats(List<Certificats> certificats) {
        this.certificats = certificats;
    }

    public Set<Mentors_to_categories> getCategories() {
        return categories;
    }

    public void setCategories(Set<Mentors_to_categories> categories) {
        this.categories = categories;
    }

    public MenteeResponseDTO getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(MenteeResponseDTO accountInfo) {
        this.accountInfo = accountInfo;
    }

    public Set<String> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<String> languages) {
        this.languages = languages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GroupServ getGroupServ() {
        return groupServ;
    }

    public void setGroupServ(GroupServ groupServ) {
        this.groupServ = groupServ;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}