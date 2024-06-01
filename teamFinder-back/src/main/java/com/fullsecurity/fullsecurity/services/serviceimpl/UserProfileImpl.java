package com.fullsecurity.fullsecurity.services.serviceimpl;

import com.fullsecurity.fullsecurity.dto.UserProfileDto;
import com.fullsecurity.fullsecurity.dto.mapper.UserProfileMapper;
import com.fullsecurity.fullsecurity.models.Skills;
import com.fullsecurity.fullsecurity.models.User;
import com.fullsecurity.fullsecurity.models.UserProfile;
import com.fullsecurity.fullsecurity.repository.UserProfileRepository;
import com.fullsecurity.fullsecurity.repository.UserRepository;
import com.fullsecurity.fullsecurity.security.services.UserDetailsImpl;
import com.fullsecurity.fullsecurity.services.UserProfileService;
import com.fullsecurity.fullsecurity.services.ViewerNotificationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserProfileImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    private final UserRepository userRepository;

    private final UserProfileMapper userProfileMapper;

    private final ViewerNotificationService viewerNotificationService;

    public UserProfileImpl(UserProfileRepository userProfileRepository, UserRepository userRepository, UserProfileMapper userProfileMapper, ViewerNotificationService viewerNotificationService) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
        this.userProfileMapper = userProfileMapper;
        this.viewerNotificationService = viewerNotificationService;
    }

    @Override
    public void addUserProfile(UserProfile userProfile) {
        if(userProfile.getId() == null) { // ne create
            User user = userRepository.findById(UserDetailsImpl.getCurrentId()).get();
          userProfile.setIsProfileCompleted(true);
          userProfile.setStatus(true);
          userProfile.setLoggedInUser(UserDetailsImpl.getCurrentId());
          userProfile.setEmail(user.getEmail());
          this.userProfileRepository.save(userProfile);
        } else { // ne update
            UserProfile userProfileCurrent = this.userProfileRepository.findById(userProfile.getId()).get();
            userProfileCurrent.setBirthday(userProfile.getBirthday());
            userProfileCurrent.setEmail(userProfile.getEmail());
            userProfileCurrent.setName(userProfile.getName());
            userProfileCurrent.setLastName(userProfile.getLastName());
            userProfileCurrent.setSkills(userProfileCurrent.getSkills());
            userProfileCurrent.setPhoneNumber(userProfileCurrent.getPhoneNumber());
            userProfileCurrent.setTimeAvailability(userProfile.getTimeAvailability());
            this.userProfileRepository.save(userProfileCurrent);
        }

    }

    @Override
    public Map<UserProfile, List<String>> getUserSkillsMap() {
        List<UserProfile> userProfiles = userProfileRepository.findAllActiveUserProfiles();
        return userProfiles.stream()
                .collect(Collectors.toMap(user -> user,
                        user -> user.getSkills().stream().map(Skills::getName).collect(Collectors.toList())));
    }

    @Override
    public UserProfileDto getUserProfile(Long id) {
        UserProfile viewerProfile = userProfileRepository.findUserProfileByLoggedInUser(UserDetailsImpl.getCurrentId());
        UserProfile userProfile = userProfileRepository.findById(id).get();
        this.viewerNotificationService.sendProfileViewNotification(viewerProfile, userProfile);
        return this.userProfileMapper.toDto(userProfile);
    }

    @Override
    public UserProfile getLoggedInUser() {
        return this.userProfileRepository.findUserProfileByLoggedInUser(UserDetailsImpl.getCurrentId());
    }
}
