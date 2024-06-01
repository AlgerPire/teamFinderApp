package com.fullsecurity.fullsecurity.repository;

import com.fullsecurity.fullsecurity.models.FounderProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FounderRepository extends JpaRepository<FounderProfile, Long> {

    FounderProfile findByLoggedInUser(Long id);
}
