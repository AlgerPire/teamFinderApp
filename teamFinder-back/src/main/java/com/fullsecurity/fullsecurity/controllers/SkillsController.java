package com.fullsecurity.fullsecurity.controllers;

import com.fullsecurity.fullsecurity.models.Skills;
import com.fullsecurity.fullsecurity.services.SkillsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/skills")
@SecurityRequirement(name = "bearerAuth")
public class SkillsController {

    private final SkillsService skillsService;

    public SkillsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Skills>> getAllSkills() {
        return ResponseEntity.ok(skillsService.findAllSkills());
    }
}
