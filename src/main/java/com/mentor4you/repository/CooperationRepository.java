package com.mentor4you.repository;

import com.mentor4you.model.Cooperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CooperationRepository extends JpaRepository<Cooperation,Integer> {

}
