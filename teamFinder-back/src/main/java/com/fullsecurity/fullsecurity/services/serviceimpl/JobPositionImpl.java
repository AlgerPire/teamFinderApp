package com.fullsecurity.fullsecurity.services.serviceimpl;

import com.fullsecurity.fullsecurity.models.JobPosition;
import com.fullsecurity.fullsecurity.models.Startup;
import com.fullsecurity.fullsecurity.repository.JobExperienceRepository;
import com.fullsecurity.fullsecurity.repository.JobPositionRepository;
import com.fullsecurity.fullsecurity.repository.StartupRepository;
import com.fullsecurity.fullsecurity.security.services.UserDetailsImpl;
import com.fullsecurity.fullsecurity.services.JobPositionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class JobPositionImpl implements JobPositionService {

    private final JobPositionRepository jobPositionRepository;

    private final StartupRepository startupRepository;

    public JobPositionImpl(JobPositionRepository jobPositionRepository, StartupRepository startupRepository) {
        this.jobPositionRepository = jobPositionRepository;
        this.startupRepository = startupRepository;
    }

    @Override
    public void addJobPosition(JobPosition jobPosition) {
        Startup startup = startupRepository.findByFounderProfileLoggedInUser(UserDetailsImpl.getCurrentId());
        jobPosition.setPostedDate(LocalDate.now());
        jobPosition.setStatus(true);
        jobPosition.setStartup(startup);
        jobPositionRepository.save(jobPosition);

    }

    @Override
    public List<JobPosition> jobPositions() {
        return jobPositionRepository.findAll();
    }
}
