package com.mentor4you.model;

import javax.persistence.*;

@Entity
@Table(name="Categories")



public class Categories {

   @OneToOne
   @JoinColumn(name = "id")
   @MapsId
   private Cooperation cooperation;

   @OneToOne (mappedBy = "categories")
   private Mentors_to_categories mentors_to_categories;


    @Id
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
