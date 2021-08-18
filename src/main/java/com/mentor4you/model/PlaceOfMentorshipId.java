package com.mentor4you.model;

import javax.persistence.*;

@Entity
@Table(name="Places_of_mentorship")
public class PlaceOfMentorshipId {
    @SequenceGenerator(
            name = "sequence_PlaceOfMentorshipId",
            sequenceName = "sequence_PlaceOfMentorshipId",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_PlaceOfMentorshipId"
    )
    private Integer id;

    private String name;

    public PlaceOfMentorshipId() {
    }

    public PlaceOfMentorshipId(String name) {
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
