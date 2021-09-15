package com.mentor4you.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "Mentors_to_categories")
public class Mentors_to_categories {


    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Categories categories;

    @ManyToOne
    @JoinColumn(name = "mentors_id")
    private Mentors mentors;

    @Id
    private int id;

    private int rate;
    private String currency;


    public Mentors_to_categories() {
    }

    public Mentors_to_categories(Categories categories,
                                 Mentors mentors,
                                 int rate,
                                 String currency) {
        this.categories = categories;
        this.mentors = mentors;
        this.rate = rate;
        this.currency = currency;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Mentors_to_categories{" +
                "id=" + id +
                ", rate=" + rate +
                ", currency='" + currency + '\'' +
                '}';
    }
}
