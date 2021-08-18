package com.mentor4you.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Accounts")
public class Account {

    @Id
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "last_visit")
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_visit;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private User user;

    @OneToOne (mappedBy = "account")
    private Mentor mentor;

    public Account() {
    }

    public Account(User user,
                   String phoneNumber,
                   Date last_visit) {
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.last_visit = last_visit;
    }

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

    public Date getLast_visit() {
        return last_visit;
    }

    public void setLast_visit(Date last_visit) {
        this.last_visit = last_visit;
    }
}
