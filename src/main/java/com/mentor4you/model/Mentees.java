package com.mentor4you.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mentees")
public class Mentees {
    @Id
    private int id;


    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Accounts accounts;




    public Mentees() {
    }

    public int getId() {
        return id;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mentees mentees = (Mentees) o;
        return id == mentees.id;
    }

    @Override
    public String toString() {
        return "Mentees{" +
                "id=" + id;
    }
}