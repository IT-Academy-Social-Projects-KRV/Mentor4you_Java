package com.mentor4you.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Cities")
public class Cities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id")
    @MapsId
    private Countries countries;

    @Column(name="name")
    private String name;

    private int country_id;


    public Cities() {

    }

    public Cities(String name, int country_id) {

        this.name = name;
        this.country_id = country_id;
    }

    public int getId() {return id;}

    public String getName() {
        return name;
    }
    public void setName(String name){this.name = name;}

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cities cities= (Cities) o;
        return id == cities.id
                && Objects.equals(name, cities.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Cities{" +
                "id=" + id +
                ", name=" + name +
                ", country_id=" + country_id +
                '}';
    }

}