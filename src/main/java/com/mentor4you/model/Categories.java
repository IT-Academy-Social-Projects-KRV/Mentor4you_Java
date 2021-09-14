package com.mentor4you.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Categories")



public class Categories {


   @OneToOne (mappedBy = "categories")
    private Cooperation cooperation;

   @OneToMany (mappedBy = "categories")
   private Set<Mentors_to_categories> mentors_to_categories;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
