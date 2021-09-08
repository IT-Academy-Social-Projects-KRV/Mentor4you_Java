package com.mentor4you.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Microdistricts")
public class Microdistricts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private Cities cities;

    @Column(name="name")
    private String name;


    public Microdistricts() {

    }

    public Microdistricts(String name, Cities cities) {

        this.name = name;
        this.cities = cities;
    }

    public int getId() {return id;}

    public String getName() {
        return name;
    }
    public void setName(String name){this.name = name;}

    public Cities getCity() {
        return cities;
    }

    public void setCities(int city_id) {
        this.cities = cities;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Microdistricts microdistricts= (Microdistricts) o;
        return id == microdistricts.id
                && Objects.equals(name, microdistricts.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Microdistricts{" +
                "id=" + id +
                ", name=" + name +
                ", city_id=" + cities +
                '}';
    }

}