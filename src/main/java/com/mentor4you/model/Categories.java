package com.mentor4you.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne (mappedBy = "categories")
    private Cooperation cooperation;

    @OneToMany (cascade = CascadeType.ALL,mappedBy = "categories")
    private List<Mentors_to_categories> mentors_to_categories;

    public Categories() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categories( String name) {
        this.name = name;
    }

    private String name;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

}
