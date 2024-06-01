package com.fullsecurity.fullsecurity.controllers;

import com.fullsecurity.fullsecurity.models.JobPosition;
import com.fullsecurity.fullsecurity.services.JobPositionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/job-position")
@SecurityRequirement(name = "bearerAuth")
public class JobPositionController {

    private final JobPositionService jobPositionService;

    public JobPositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobPosition>> getAllJobPosition() {
        try {
            return ResponseEntity.ok(jobPositionService.jobPositions());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
