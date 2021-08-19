package com.mentor4you.model;

import javax.persistence.*;

@Entity
@Table(name="groupServices")
public class GroupServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public GroupServices() {
    }

    public GroupServices(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
