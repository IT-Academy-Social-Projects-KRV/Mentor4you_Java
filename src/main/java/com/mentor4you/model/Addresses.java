package com.mentor4you.model;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Addresses")
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id")
    @MapsId
    private Mentors_to_microdistricts mentors_to_microdistricts;

    private int mentors_to_microdistricts_id;
    public Addresses() {

    }

    public Addresses(String name, int mentors_to_microdistricts_id) {

        this.name = name;
        this.mentors_to_microdistricts_id =  mentors_to_microdistricts_id;
    }

    public int getId() {return id;}

    public String getName() {
        return name;
    }
    public void setName(String name){this.name = name;}

    public int getMentors_to_microdistricts_id() {
        return mentors_to_microdistricts_id;
    }

    public void setMentors_to_microdistricts_id(int mentors_to_microdistricts_id) {
        this.mentors_to_microdistricts_id = mentors_to_microdistricts_id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Addresses addresses = (Addresses) o;
        return id == addresses.id
                && Objects.equals(name, addresses.name)
                && Objects.equals(mentors_to_microdistricts_id, addresses.mentors_to_microdistricts_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Addresses{" +
                "id=" + id +
                ", name=" + name +
                ", mentors_to_microdistricts_id=" + mentors_to_microdistricts_id +
                '}';
    }

}