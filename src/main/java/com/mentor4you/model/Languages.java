package com.mentor4you.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "languages")
public class Languages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "languagesList",
            cascade = {CascadeType.MERGE,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Set<Accounts> accountsList;

    public Set<Accounts> getAccountsList() {
        return accountsList;
    }

    public void addAccounts(Accounts accounts) {
        if(accountsList.isEmpty()) accountsList = new HashSet<>();
        this.accountsList.add(accounts);
    }

    public void removeAccounts(Accounts accounts) {
        if(!accountsList.isEmpty()) {
            accountsList.remove(accounts);
            accounts.getLanguagesList().remove(this);
        }
    }

    public Languages() {
    }

    public Languages(String name) {
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
        Languages languages = (Languages) o;
        return Objects.equals(id, languages.id) && Objects.equals(name, languages.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Languages{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
