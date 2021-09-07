package com.mentor4you.model;

import javax.persistence.*;
import java.sql.Struct;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "countries")
public class Countries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;

    public Countries(String name) {
        this.name = name;
    }

    public Countries() {

    }

    public int getId() {return id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Countries countries = (Countries) o;
        return id == countries.id
                && Objects.equals(name, countries.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Countries{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}