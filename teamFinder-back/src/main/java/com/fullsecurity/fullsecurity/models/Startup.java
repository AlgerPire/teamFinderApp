package com.fullsecurity.fullsecurity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "startup")
@SQLRestriction("status = true")
public class Startup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean status;

    private String name;

    private String description;

    private LocalDate foundingDate;

    @ManyToMany
    @JoinTable(name = "startup_techStack",
            joinColumns = @JoinColumn(name = "startup_id"),
            inverseJoinColumns = @JoinColumn(name = "skills_id"))
    private List<Skills> techStack;

    @ManyToOne
    @JoinColumn(name = "founder_profile_id", nullable = false)
    private FounderProfile founderProfile;


}
