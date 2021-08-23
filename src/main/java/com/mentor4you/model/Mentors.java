package com.mentor4you.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Mentors")
public class Mentors {
    @Id
    private int id;

    private String description;

    private boolean showable_status;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Accounts accounts;

    @ManyToOne
    @JoinColumn(name = "group_services")
    private GroupServices group_services;

    private boolean is_online;
    private boolean is_offline_in;
    private boolean is_ofline_out;

    public Mentors() {
    }

    public Mentors(Accounts accounts,
                   String description,
                   boolean showable_status,
                   GroupServices group_services,
                   boolean is_online,
                   boolean is_offline_in,
                   boolean is_ofline_out
    ) {
        this.accounts = accounts;
        this.description = description;
        this.showable_status = showable_status;
        this.group_services = group_services;
        this.is_online = is_online;
        this.is_offline_in = is_offline_in;
        this.is_ofline_out = is_ofline_out;
    }

    public int getId() {
        return id;
    }

    public Accounts getAccount() {
        return accounts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isShowable_status() {
        return showable_status;
    }

    public void setShowable_status(boolean showable_status) {
        this.showable_status = showable_status;
    }

    public GroupServices getGroup_services() {
        return group_services;
    }

    public void setGroup_services(GroupServices group_services) {
        this.group_services = group_services;
    }

    public boolean isIs_online() {
        return is_online;
    }

    public void setIs_online(boolean is_online) {
        this.is_online = is_online;
    }

    public boolean isIs_offline_in() {
        return is_offline_in;
    }

    public void setIs_offline_in(boolean is_offline_in) {
        this.is_offline_in = is_offline_in;
    }

    public boolean isIs_ofline_out() {
        return is_ofline_out;
    }

    public void setIs_ofline_out(boolean is_ofline_out) {
        this.is_ofline_out = is_ofline_out;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mentors mentors = (Mentors) o;
        return id == mentors.id
                && showable_status == mentors.showable_status
                && is_online == mentors.is_online
                && is_offline_in == mentors.is_offline_in
                && is_ofline_out == mentors.is_ofline_out;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                description,
                showable_status,
                accounts,
                group_services,
                is_online,
                is_offline_in,
                is_ofline_out);
    }

    @Override
    public String toString() {
        return "Mentors{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", showable_status=" + showable_status +
                ", group_services=" + group_services +
                ", is_online=" + is_online +
                ", is_offline_in=" + is_offline_in +
                ", is_ofline_out=" + is_ofline_out +
                '}';
    }
}
