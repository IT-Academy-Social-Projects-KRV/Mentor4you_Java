package com.mentor4you.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Cooperation")
public class Cooperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "mentees_id")
    private Mentees mentees;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentors mentors;


    private int status;


    public Cooperation() {
    }


}
