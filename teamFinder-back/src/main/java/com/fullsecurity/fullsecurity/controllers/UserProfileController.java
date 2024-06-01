package com.fullsecurity.fullsecurity.controllers;
import com.fullsecurity.fullsecurity.dto.UserProfileDto;
import com.fullsecurity.fullsecurity.models.JobPosition;
import com.fullsecurity.fullsecurity.models.UserProfile;
import com.fullsecurity.fullsecurity.payload.response.ApiResponse;
import com.fullsecurity.fullsecurity.security.services.UserDetailsImpl;
import com.fullsecurity.fullsecurity.services.JobMatchingService;
import com.fullsecurity.fullsecurity.services.UserProfileService;
import com.fullsecurity.fullsecurity.services.serviceimpl.MatchingAlgorithm;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user-profile")
@SecurityRequirement(name = "bearerAuth")
public class UserProfileController {

    private final UserProfileService userProfileService;

    private final MatchingAlgorithm matchingAlgorithm;

    private final JobMatchingService jobMatchingService;

    private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    public UserProfileController(UserProfileService userProfileService, MatchingAlgorithm matchingAlgorithm, JobMatchingService jobMatchingService) {
        this.userProfileService = userProfileService;
        this.matchingAlgorithm = matchingAlgorithm;
        this.jobMatchingService = jobMatchingService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse> saveUserProfile(@RequestBody UserProfile userProfile) {
        logger.debug("Rest request to save user profile");
        try {
            this.userProfileService.addUserProfile(userProfile);
            return ResponseEntity.ok().body(new ApiResponse(HttpStatus.OK, "Profili u shtua me sukses"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Ka ndodhur"));
        }
    }

    @GetMapping("/matches")
    @PreAuthorize("hasRole('USER')")
    public List<UserProfile> getMatches() {
        Long loggedInUser = UserDetailsImpl.getCurrentId();
        Map<UserProfile, List<String>> userSkillsMap = userProfileService.getUserSkillsMap();
        return matchingAlgorithm.findMatches(loggedInUser, userSkillsMap);
    }

    @GetMapping("/user-profile/{id}")
    public ResponseEntity<UserProfileDto> getUserProfile(@PathVariable Long id) {
        logger.debug("Request to get a user profile");
        try {
           UserProfileDto userProfile = this.userProfileService.getUserProfile(id);
            return ResponseEntity.ok(userProfile);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-logged-user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserProfile> getLoggedInUser() {
        logger.debug("Request to get LoggedIn User");
        try {
            return ResponseEntity.ok(userProfileService.getLoggedInUser());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/job-matching")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<JobPosition>> getMatchedJobPosition() {
        logger.debug("Request to get matched job position");
        try {
            return ResponseEntity.ok(jobMatchingService.suggestJobsForUser(UserDetailsImpl.getCurrentId()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
