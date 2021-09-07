package com.mentor4you.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Mentors_to_microdistricts")
public class Mentors_to_microdistricts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "microdisctrict_id")
    private Microdistricts microdistrict;

    @ManyToOne
    @JoinColumn(name = "accounts_id")
    private Mentors mentor;


    public Mentors_to_microdistricts() {
    }

    public Mentors_to_microdistricts(Microdistricts microdistrict, Mentors mentor) {
        this.microdistrict = microdistrict;
        this.mentor = mentor;
    }

    public int getId() {
        return id;
    }

    public Microdistricts getMicrodistrict_id() {
        return microdistrict;
    }

    public void setMicrodistrict_id(Microdistricts microdistrict) {
        this.microdistrict = microdistrict;
    }

    public Mentors getMentorId() {
        return mentor;
    }

    public void setMentor_id(Mentors mentor_id) {
        this.mentor = mentor_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mentors_to_microdistricts that = (Mentors_to_microdistricts) o;
        return id == that.id
                && Objects.equals(microdistrict, that.microdistrict)
                && Objects.equals(mentor, that.mentor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, microdistrict, mentor);
    }

    @Override
    public String toString() {
        return "Mentors_to_microdictricts{" +
                "id=" + id +
                ", microdictrict_id=" + microdistrict +
                ", mentor_id=" + mentor +
                '}';
    }

}