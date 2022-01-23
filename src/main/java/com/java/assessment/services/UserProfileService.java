
package com.java.assessment.services;

import com.java.assessment.error.RecordNotFoundException;
import com.java.assessment.model.UserProfile;

import java.util.List;

public interface UserProfileService {
	UserProfile saveProfile(UserProfile profile)throws RecordNotFoundException;
	UserProfile updateProfile(UserProfile profile) throws RecordNotFoundException;
	List<UserProfile> getAllUsers();
	UserProfile getProfileInfo(Long id) throws RecordNotFoundException;
}
