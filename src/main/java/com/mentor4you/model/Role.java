package com.mentor4you.model;

import javax.persistence.*;

@Entity
@Table(name="Roles")

public class Role {

    @SequenceGenerator(
            name = "sequence_Roles",
            sequenceName = "sequence_Roles",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_Roles"
    )
    private Integer id;

    private String name;

    public Role() {
    }

    public Role(String name) {

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
