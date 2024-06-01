package com.fullsecurity.fullsecurity.services;

import com.fullsecurity.fullsecurity.models.FounderProfile;
import com.fullsecurity.fullsecurity.models.UserProfile;

import java.util.List;
import java.util.Map;

public interface FounderService {
    void addFounder(FounderProfile founderProfile);

    FounderProfile getFounderProfile(Long loggedIn);
}
