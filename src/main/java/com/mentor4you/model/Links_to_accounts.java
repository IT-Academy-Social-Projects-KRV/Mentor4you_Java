package com.mentor4you.model;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name="Links_to_accounts")
public class Links_to_accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "social_networks_id")
    private Social_networks social_networks;

    @ManyToOne
    @JoinColumn(name = "accounts_id")
    private Accounts accounts;

    private String url;

    public Links_to_accounts() {
    }

    public Links_to_accounts(Social_networks social_networks, Accounts accounts, String url) {
        this.social_networks = social_networks;
        this.accounts = accounts;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public Social_networks getSocial_networks() {
        return social_networks;
    }

    public void setSocial_networks(Social_networks social_networks) {
        this.social_networks = social_networks;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Links_to_accounts that = (Links_to_accounts) o;
        return id == that.id
                && Objects.equals(social_networks, that.social_networks)
                && Objects.equals(accounts, that.accounts)
                && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, social_networks, accounts, url);
    }

    @Override
    public String toString() {
        return "Links_to_accounts{" +
                "id=" + id +
                ", social_networks=" + social_networks +
                ", accounts=" + accounts +
                ", url='" + url + '\'' +
                '}';
    }
}
