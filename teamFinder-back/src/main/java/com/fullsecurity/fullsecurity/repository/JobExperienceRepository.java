package com.fullsecurity.fullsecurity.repository;

import com.fullsecurity.fullsecurity.models.JobExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobExperienceRepository extends JpaRepository<JobExperience, Long> {


}
