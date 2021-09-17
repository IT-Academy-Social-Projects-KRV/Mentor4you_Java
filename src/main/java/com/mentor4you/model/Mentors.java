package com.mentor4you.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "Mentors")
public class Mentors {
    @Id
    private int id;

    private String description;

    private boolean showable_status;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Accounts accounts;

    @ManyToOne
    @JoinColumn(name = "group_services")
    private GroupServices group_services;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "mentors")
    private Set<Mentors_to_categories> mentors_to_categories;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="education")
    private List<Educations> educations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="certificats")
    private List<Certificats> certificats;

    @Column(name = "is_online")
    private boolean isOnline;

    @Column(name = "is_offline_in")
    private boolean isOfflineIn;

    @Column(name = "is_offline_out")
    private boolean isOfflineOut;


    public Mentors() {
    }

    public Mentors(Accounts accounts,
                   String description,
                   boolean showable_status,
                   GroupServices group_services,
                   boolean is_online,
                   boolean is_offline_in,
                   boolean is_offline_out
    ) {
        this.accounts = accounts;
        this.description = description;
        this.showable_status = showable_status;
        this.group_services = group_services;
        this.isOnline = is_online;
        this.isOfflineIn = is_offline_in;
        this.isOfflineOut = is_offline_out;
    }

    public int getId() {
        return id;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
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

    public GroupServices getGroup_services() {
        return group_services;
    }

    public void setGroup_services(GroupServices group_services) {
        this.group_services = group_services;
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

    public Set<Mentors_to_categories> getMentors_to_categories() {
        return mentors_to_categories;
    }

    public void setMentors_to_categories(Set<Mentors_to_categories> mentors_to_categories) {
        this.mentors_to_categories = mentors_to_categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mentors mentors = (Mentors) o;
        return id == mentors.id
                && showable_status == mentors.showable_status;
    }

   /* @Override
    public int hashCode() {
        return Objects.hash(id,
                description,
                showable_status,
                accounts,
                group_services,
                is_online,
                is_offline_in,
                is_offline_out);
    }*/

    @Override
    public String toString() {
        return "Mentors{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", showable_status=" + showable_status +
                ", accounts=" + accounts +
                ", group_services=" + group_services +
                ", mentors_to_categories=" + mentors_to_categories +
                ", educations=" + educations +
                ", certificats=" + certificats +
                ", isOnline=" + isOnline +
                ", isOfflineIn=" + isOfflineIn +
                ", isOfflineOut=" + isOfflineOut +
                '}';
    }
}