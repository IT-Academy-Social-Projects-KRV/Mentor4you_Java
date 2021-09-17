package com.mentor4you.service.requests;


import com.mentor4you.model.Certificats;
import com.mentor4you.model.Educations;
import com.mentor4you.model.GroupServices;
import com.mentor4you.model.Mentors_to_categories;

import java.util.List;
import java.util.Set;

public class MentorGeneralDTO {

    private String description;
    private boolean showable_status;
    private boolean isOnline;
    private boolean isOfflineIn;
    private boolean isOfflineOut;

    private List<Educations> educations;

    private List<Certificats> certificats;

    private Set<Mentors_to_categories> categories;


    public MentorGeneralDTO() {
    }

    public MentorGeneralDTO(String description,
                            boolean showable_status,
                            boolean isOnline,
                            boolean isOfflineIn,
                            boolean isOfflineOut,
                            List<Educations> educations,
                            List<Certificats> certificats,
                            Set<Mentors_to_categories> categories) {
        this.description = description;
        this.showable_status = showable_status;
        this.isOnline = isOnline;
        this.isOfflineIn = isOfflineIn;
        this.isOfflineOut = isOfflineOut;
        this.educations = educations;
        this.certificats = certificats;
        this.categories = categories;
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
}
