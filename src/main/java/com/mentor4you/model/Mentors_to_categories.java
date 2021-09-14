package com.mentor4you.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="Mentors_to_categories")


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

    public Mentors_to_categories(int id, int category_id, int mentor_id, int rate, String currency) {
        this.id = id;
        this.category_id = category_id;
        this.mentor_id = mentor_id;
        this.rate = rate;
        this.currency = currency;
    }

    public Mentors_to_categories() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mentors_to_categories that = (Mentors_to_categories) o;
        return id == that.id && category_id == that.category_id && mentor_id == that.mentor_id && rate == that.rate && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category_id, mentor_id, rate, currency);
    }

    private int category_id;
    private int mentor_id;
    private int rate;
    private String currency;


    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getMentor_id() {
        return mentor_id;
    }

    public void setMentor_id(int mentor_id) {
        this.mentor_id = mentor_id;
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
                ", category_id=" + category_id +
                ", mentor_id=" + mentor_id +
                ", rate=" + rate +
                ", currency='" + currency + '\'' +
                '}';
    }





}
