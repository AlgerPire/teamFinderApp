package com.fullsecurity.fullsecurity.services;

import com.fullsecurity.fullsecurity.models.FounderProfile;

public interface FounderService {
    void addFounder(FounderProfile founderProfile);

    FounderProfile getFounderProfile(Long loggedIn);
}
