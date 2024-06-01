package com.fullsecurity.fullsecurity.services.serviceimpl;

import com.fullsecurity.fullsecurity.models.Skills;
import com.fullsecurity.fullsecurity.repository.SkillsRepository;
import com.fullsecurity.fullsecurity.services.SkillsService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SkillsImpl implements SkillsService {

    private final SkillsRepository skillsRepository;

    public SkillsImpl(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    @Override
    public List<Skills> findAllSkills() {
        return skillsRepository.findAll();
    }
}
