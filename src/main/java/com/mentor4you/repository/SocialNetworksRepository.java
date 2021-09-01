package com.mentor4you.repository;

import com.mentor4you.model.Social_networks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SocialNetworksRepository extends JpaRepository<Social_networks, Integer> {

    Optional<Social_networks> findById(int id);

    @Query("Select sn from Social_networks sn WHERE sn.name =?1")
    Social_networks findByName(String name);
}
