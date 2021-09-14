package com.mentor4you.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Cooperation")
public class Cooperation {
    @Id
    private int id;


    @ManyToOne
    @JoinColumn(name = "id")
    @MapsId
    private Mentees mentees;

    @ManyToOne
    @JoinColumn(name = "id")
    @MapsId
    private Mentors mentors;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Categories categories;


    private int mentee_id;

    private int mentor_id;
    private int status;
    private int date_time;
    private int categories_id;

    public Cooperation() {
    }

    public Cooperation(int mentee_id,
                       int mentor_id,
                       int status,
                       int date_time,
                       int categories_id

    ) {

        this.mentee_id = mentee_id;
        this.mentor_id = mentor_id;
        this.status = status;
        this.date_time = date_time;
        this.categories_id = categories_id;

    }

    public int getId() {
        return id;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cooperation cooperation = (Cooperation) o;
        return id == cooperation.id
                && mentee_id == cooperation.mentee_id
                && mentor_id == cooperation.mentor_id
                && status == cooperation.status
                && date_time == cooperation.date_time
                && categories_id == cooperation.categories_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                mentee_id,
                mentor_id,
                status,
                date_time,
                categories_id
               );
    }

    @Override
    public String toString() {
        return "Cooperation{" +
                "id=" + id +
                ", mentee_id='" + mentee_id + '\'' +
                ", mentor_id=" + mentor_id +
                ", status=" + status +
                ", date_time=" + date_time +
                ", categories_id=" + categories_id +
                '}';
    }
}
