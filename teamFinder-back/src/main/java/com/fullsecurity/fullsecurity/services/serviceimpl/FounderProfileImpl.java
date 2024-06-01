package com.fullsecurity.fullsecurity.services.serviceimpl;

import com.fullsecurity.fullsecurity.models.FounderProfile;
import com.fullsecurity.fullsecurity.repository.FounderRepository;
import com.fullsecurity.fullsecurity.security.services.UserDetailsImpl;
import com.fullsecurity.fullsecurity.services.FounderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FounderProfileImpl implements FounderService {

    private final FounderRepository founderRepository;

    public FounderProfileImpl(FounderRepository founderRepository) {
        this.founderRepository = founderRepository;
    }

    @Override
    public void addFounder(FounderProfile founderProfile) {
        if(founderProfile.getId() == null) { // create
            founderProfile.setLoggedInUser(UserDetailsImpl.getCurrentId());
            founderProfile.setStatus(true);
            founderProfile.setIsProfileCompleted(true);
            founderRepository.save(founderProfile);
        } else { // update
            FounderProfile founderProfile1 = new FounderProfile();
            founderProfile1.setEmail(founderProfile.getEmail());
            founderProfile1.setDescription(founderProfile.getDescription());
            founderProfile1.setName(founderProfile.getName());
            founderProfile1.setBirthday(founderProfile.getBirthday());
            founderProfile1.setLastName(founderProfile.getLastName());
            founderProfile1.setPhoneNumber(founderProfile.getPhoneNumber());
            founderRepository.save(founderProfile1);

        }

    }

    @Override
    public FounderProfile getFounderProfile(Long loggedIn) {
        return this.founderRepository.findByLoggedInUser(loggedIn);
    }
}
