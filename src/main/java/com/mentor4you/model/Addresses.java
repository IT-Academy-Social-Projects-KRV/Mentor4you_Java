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

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Mentors_to_microdistricts mentors_to_microdistricts;

    public Addresses() {

    }

    public Addresses(Mentors_to_microdistricts mentors_to_microdistricts,String name) {

        this.name = name;
        this.mentors_to_microdistricts =  mentors_to_microdistricts;
    }

    public int getId() {return id;}

    public String getName() {
        return name;
    }
    public void setName(String name){this.name = name;}

    public Mentors_to_microdistricts getMentors_to_microdistricts() {
        return mentors_to_microdistricts;
    }

    public void setMentors_to_microdistricts(Mentors_to_microdistricts mentors_to_microdistricts) {
        this.mentors_to_microdistricts = mentors_to_microdistricts;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Addresses addresses = (Addresses) o;
        return id == addresses.id
                && Objects.equals(name, addresses.name)
                && Objects.equals(mentors_to_microdistricts, addresses.mentors_to_microdistricts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Addresses{" +
                "id=" + id +
                ", name=" + name+
                '}';
    }

}