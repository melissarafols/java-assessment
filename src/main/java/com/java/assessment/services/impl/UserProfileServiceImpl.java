package com.java.assessment.services.impl;

import com.java.assessment.error.RecordNotFoundException;
import com.java.assessment.model.UserProfile;
import com.java.assessment.repository.UserProfileRepository;
import com.java.assessment.services.UserProfileService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public UserProfile saveProfile(UserProfile profile) throws RecordNotFoundException {
        profile = userProfileRepository.save(profile);
        return profile;
    }

    @Override
    public UserProfile updateProfile(UserProfile profile) throws RecordNotFoundException {
        Long id = Long.valueOf(profile.getId());
        Optional<UserProfile> userPofile = userProfileRepository.findById(id);


        if (userPofile.isPresent()) {
            if (StringUtils.isBlank(profile.getFirstName())) {
                profile.setFirstName(userPofile.get().getFirstName());
            }

            if (StringUtils.isBlank(profile.getLastName())) {
                profile.setLastName(userPofile.get().getLastName());
            }

            if (StringUtils.isBlank(profile.getUsername())) {
                profile.setUsername(userPofile.get().getUsername());
            }
            profile = userProfileRepository.save(profile);

        }
        return profile;
    }

    @Override
    public List<UserProfile> getAllUsers() {
        List<UserProfile> employeeList = userProfileRepository.findAll();

        if (employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<UserProfile>();
        }

    }

    @Override
    public UserProfile getProfileInfo(Long id) throws RecordNotFoundException {
        Optional<UserProfile> user = userProfileRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }
}
