package com.fullsecurity.fullsecurity.repository;

import com.fullsecurity.fullsecurity.models.Startup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StartupRepository extends JpaRepository<Startup, Long> {

    Startup findByFounderProfileLoggedInUser(Long id);
}
