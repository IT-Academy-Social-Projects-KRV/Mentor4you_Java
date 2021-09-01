package com.mentor4you.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Social_networks")
public class Social_networks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    private String name;

    @OneToMany(mappedBy = "social_networks")
    private Set<Links_to_accounts> links_to_accounts;

    public Social_networks() {}

    public Social_networks(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Social_networks that = (Social_networks) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(links_to_accounts, that.links_to_accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, links_to_accounts);
    }

    @Override
    public String toString() {
        return "Social_networks{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", links_to_accounts=" + links_to_accounts +
                '}';
    }
}
