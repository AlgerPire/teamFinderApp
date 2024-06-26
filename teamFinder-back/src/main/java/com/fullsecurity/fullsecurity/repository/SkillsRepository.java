package com.fullsecurity.fullsecurity.repository;

import com.fullsecurity.fullsecurity.models.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {
}
