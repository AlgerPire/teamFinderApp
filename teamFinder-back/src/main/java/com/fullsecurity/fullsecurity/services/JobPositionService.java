package com.fullsecurity.fullsecurity.services;

import com.fullsecurity.fullsecurity.models.JobPosition;

import java.util.List;

public interface JobPositionService {

    void addJobPosition(JobPosition jobPosition);

    List<JobPosition> jobPositions();
}
