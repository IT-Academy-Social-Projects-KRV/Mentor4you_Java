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
    @JoinColumn(name = "id")
    @MapsId
    private Cities cities;

    @Column(name="name")
    private String name;

    private int city_id;

    public Microdistricts() {

    }

    public Microdistricts(String name, int city_id) {

        this.name = name;
        this.city_id = city_id;
    }

    public int getId() {return id;}

    public String getName() {
        return name;
    }
    public void setName(String name){this.name = name;}

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
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
                '}';
    }

}