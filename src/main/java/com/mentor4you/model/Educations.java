package com.mentor4you.model;

import javax.persistence.*;

@Entity
@Table(name = "Educations")
public class Educations{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;


    public Educations(){}

    public Educations(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Educations{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
