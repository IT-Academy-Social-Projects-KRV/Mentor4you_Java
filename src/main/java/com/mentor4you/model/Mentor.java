package com.mentor4you.model;

import javax.persistence.*;

@Entity
@Table(name="Mentors")
public class Mentor {
    @Id
    private Long id;

    private String description;

    @Column(name = "showable_status")
    private boolean showableStatus;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Account account;

    @ManyToOne
    @JoinColumn (name = "placeOfMentorship_id")
    private PlaceOfMentorshipId placeOfMentorshipId;

    @ManyToOne
    @JoinColumn (name = "group_services")
    private GroupServices groupServices;

    public Mentor() {
    }

    public Mentor(Account account,
                  String description,
                  boolean showableStatus,
                  PlaceOfMentorshipId placeOfMentorshipId,
                  GroupServices groupServices) {
        this.account = account;
        this.description = description;
        this.showableStatus = showableStatus;
        this.placeOfMentorshipId = placeOfMentorshipId;
        this.groupServices = groupServices;
    }

    public Account getAccount() {
        return account;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isShowableStatus() {
        return showableStatus;
    }

    public void setShowableStatus(boolean showableStatus) {
        this.showableStatus = showableStatus;
    }

    public PlaceOfMentorshipId getPlaceOfMentorshipId() {
        return placeOfMentorshipId;
    }


    public GroupServices getGroupServices() {
        return groupServices;
    }


}
