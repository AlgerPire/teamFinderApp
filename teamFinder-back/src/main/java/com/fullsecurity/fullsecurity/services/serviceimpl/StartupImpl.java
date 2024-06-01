package com.fullsecurity.fullsecurity.services.serviceimpl;

import com.fullsecurity.fullsecurity.models.FounderProfile;
import com.fullsecurity.fullsecurity.models.Startup;
import com.fullsecurity.fullsecurity.repository.FounderRepository;
import com.fullsecurity.fullsecurity.repository.StartupRepository;
import com.fullsecurity.fullsecurity.security.services.UserDetailsImpl;
import com.fullsecurity.fullsecurity.services.StartupService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
public class StartupImpl implements StartupService {

    private final StartupRepository startupRepository;

    private final FounderRepository founderRepository;

    public StartupImpl(StartupRepository startupRepository, FounderRepository founderRepository) {
        this.startupRepository = startupRepository;
        this.founderRepository = founderRepository;
    }

    @Override
    public void addStartup(Startup startup) {
        FounderProfile founderProfile = founderRepository.findByLoggedInUser(UserDetailsImpl.getCurrentId());
        startup.setFoundingDate(LocalDate.now());
        startup.setStatus(true);
        startup.setFounderProfile(founderProfile);
        startupRepository.save(startup);
    }


}
