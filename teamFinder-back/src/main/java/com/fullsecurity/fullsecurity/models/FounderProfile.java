package com.fullsecurity.fullsecurity.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "founder_profile")
@SQLRestriction("status = true")
public class FounderProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean status;

    private Boolean isProfileCompleted;

    private String name;

    private String lastName;

    private LocalDate birthday;

    private String phoneNumber;

    private String description;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Long loggedInUser;


}
