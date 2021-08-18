package com.mentor4you.model;

import javax.persistence.*;

@Entity
@Table(name="groupServices")
public class GroupServices {
    @SequenceGenerator(
            name = "sequence_GroupServices",
            sequenceName = "sequence_GroupServices",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_GroupServices"
    )
    private Integer id;

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
