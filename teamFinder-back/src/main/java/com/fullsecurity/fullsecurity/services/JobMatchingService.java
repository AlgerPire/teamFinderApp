package com.fullsecurity.fullsecurity.services;

import com.fullsecurity.fullsecurity.models.JobPosition;

import java.util.List;

public interface JobMatchingService {

    List<JobPosition> suggestJobsForUser(Long userId);
}
