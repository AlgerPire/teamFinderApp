package com.fullsecurity.fullsecurity.services;

import com.fullsecurity.fullsecurity.dto.UserProfileDto;
import com.fullsecurity.fullsecurity.models.UserProfile;

import java.util.List;
import java.util.Map;

public interface UserProfileService {

    void addUserProfile(UserProfile userProfile);

    Map<UserProfile, List<String>> getUserSkillsMap();

    UserProfileDto getUserProfile(Long id);

    UserProfile getLoggedInUser();
}
