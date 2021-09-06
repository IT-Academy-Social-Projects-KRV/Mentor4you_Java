package com.mentor4you.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mentor4you.service.requests.UpdateAccountRequest;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="Accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "last_visit")
    private LocalDateTime last_visit;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private User user;

    @OneToOne (mappedBy = "accounts")
    private Mentors mentors;

    @OneToOne (mappedBy = "accounts")
    private Mentees mentees;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "languages_to_accounts",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "languages_id")
    )
    private List<Languages> languagesList;
    @OneToMany(mappedBy = "accounts")
    private Set<Links_to_accounts> links_to_accounts;

    public Accounts(User user,
                    String phoneNumber,
                    LocalDateTime last_visit) {
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.last_visit = last_visit;
    }

    public Accounts() {

    }

    public int getId() {return id;}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getLast_visit() {
        return last_visit;
    }

    public void setLast_visit(LocalDateTime last_visit) {
        this.last_visit = last_visit;
    }

    public List<Languages> getLanguagesList() {
        return languagesList;
    }

    public void addLanguages(Languages languages) {
        if(languagesList.isEmpty()) languagesList = new ArrayList<>();
        this.languagesList.add(languages);
        languages.addAccounts(this);
    }
    public  void UpdateAccountReq(UpdateAccountRequest update){
        this.phoneNumber = update.getPhoneNumber();

        this.languagesList = update.getLanguagesList();
        this.user.updateUserReq(update.getUpdateUserRequest());

    }
    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts accounts = (Accounts) o;
        return id == accounts.id
                && Objects.equals(phoneNumber, accounts.phoneNumber)
                && Objects.equals(last_visit, accounts.last_visit)
                && Objects.equals(user, accounts.user)
                && Objects.equals(mentors, accounts.mentors);
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, last_visit, user, mentors);
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", last_visit=" + last_visit +
                ", user id=" + user.getId() +
                ", mentors=" + mentors.getId() +
                '}';
    }
}
