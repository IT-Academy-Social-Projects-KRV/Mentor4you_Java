package com.mentor4you.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "languages_to_accounts",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "languages_id")
    )
    private List<Languages> languagesList;

    public Accounts(User user,
                    String phoneNumber,
                    LocalDateTime last_visit) {
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.last_visit = last_visit;
    }

    public Accounts() {

    }

    public List<Languages> getLanguagesList() {
        return languagesList;
    }

    public void addLanguages(Languages languages) {
        if(languagesList.isEmpty()) languagesList = new ArrayList<>();
        this.languagesList.add(languages);
        languages.addAccounts(this);
    }

    public int getId() {return id;}

    public Mentors getMentor() {return mentors;}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts accounts = (Accounts) o;
        return id == accounts.id
                && Objects.equals(phoneNumber, accounts.phoneNumber)
                && Objects.equals(last_visit, accounts.last_visit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, last_visit);
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", last_visit=" + last_visit +
                '}';
    }
}
