package com.fullsecurity.fullsecurity.services.serviceimpl;

import com.fullsecurity.fullsecurity.models.JobPosition;
import com.fullsecurity.fullsecurity.models.Skills;
import com.fullsecurity.fullsecurity.models.UserProfile;
import com.fullsecurity.fullsecurity.repository.JobPositionRepository;
import com.fullsecurity.fullsecurity.repository.UserProfileRepository;
import com.fullsecurity.fullsecurity.services.JobMatchingService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class JobMatchingServiceImpl implements JobMatchingService {

    private final UserProfileRepository userProfileRepository;

    private final JobPositionRepository jobPositionRepository;


    public JobMatchingServiceImpl(UserProfileRepository userProfileRepository, JobPositionRepository jobPositionRepository) {
        this.userProfileRepository = userProfileRepository;
        this.jobPositionRepository = jobPositionRepository;
    }

    public List<JobPosition> suggestJobsForUser(Long loggedUserId) {
        UserProfile userProfile = userProfileRepository.findUserProfileByLoggedInUser(loggedUserId);
        if (userProfile == null || userProfile.getSkills() == null || userProfile.getSkills().isEmpty()) {
            return Collections.emptyList();
        }

        List<JobPosition> allJobPositions = jobPositionRepository.findAll();
        Set<String> userSkills = userProfile.getSkills().stream().map(Skills::getName).collect(Collectors.toSet());

        int minimumRequiredMatches = userSkills.size() / 2;

        return allJobPositions.stream()
                .filter(job -> hasMinimumMatchingSkills(job, userSkills, minimumRequiredMatches))
                .sorted(Comparator.comparingInt(job -> calculateMatchingScore(job, userSkills)))
                .collect(Collectors.toList());
    }

    private boolean hasMinimumMatchingSkills(JobPosition jobPosition, Set<String> userSkills, int minimumRequiredMatches) {
        Set<String> jobSkills = jobPosition.getSkills().stream().map(Skills::getName).collect(Collectors.toSet());
        jobSkills.retainAll(userSkills);  // Get the intersection of job skills and user skills
        return jobSkills.size() >= minimumRequiredMatches;  // Check if the number of matching skills is at least half of the user's skills
    }

    private int calculateMatchingScore(JobPosition jobPosition, Set<String> userSkills) {
        Set<String> jobSkills = jobPosition.getSkills().stream().map(Skills::getName).collect(Collectors.toSet());
        jobSkills.retainAll(userSkills);  // Get the intersection of job skills and user skills
        return jobSkills.size();          // The more skills match, the higher the score
    }

}
