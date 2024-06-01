package com.fullsecurity.fullsecurity.controllers;

import com.fullsecurity.fullsecurity.models.FounderProfile;
import com.fullsecurity.fullsecurity.models.JobPosition;
import com.fullsecurity.fullsecurity.models.Startup;
import com.fullsecurity.fullsecurity.payload.response.ApiResponse;
import com.fullsecurity.fullsecurity.security.services.UserDetailsImpl;
import com.fullsecurity.fullsecurity.services.FounderService;
import com.fullsecurity.fullsecurity.services.JobPositionService;
import com.fullsecurity.fullsecurity.services.StartupService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/founder-profile")
@SecurityRequirement(name = "bearerAuth")
public class FounderController {

    private final FounderService founderService;

    private final StartupService startupService;

    private final JobPositionService jobPositionService;

    public FounderController(FounderService founderService, StartupService startupService, JobPositionService jobPositionService) {
        this.founderService = founderService;
        this.startupService = startupService;
        this.jobPositionService = jobPositionService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> addFounder(@RequestBody FounderProfile founderProfile) {
        try {
            this.founderService.addFounder(founderProfile);
            return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Founderi u shtua me sukses!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Ka ndodhur një problem!"));
        }
    }

    @GetMapping("/get-profile")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FounderProfile> getFounderProfile() {
        try {
            return ResponseEntity.ok(this.founderService.getFounderProfile(UserDetailsImpl.getCurrentId()));
        } catch (Exception e) {
            e.printStackTrace();
            return new  ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-startup")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> addStartup(@RequestBody Startup startup) {
        try {
            this.startupService.addStartup(startup);
            return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Startup u shtua me sukses"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Ka ndodhur nje problem"));
        }
    }

    @PostMapping("/add-job")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> addJobPosition(@RequestBody JobPosition jobPosition) {
        try {
            this.jobPositionService.addJobPosition(jobPosition);
            return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Pozicioni i punes u shtua me sukses"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Ka ndodhur nje problem"));
        }
    }
}
