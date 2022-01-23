package com.java.assessment.repository;


import com.java.assessment.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository
        extends JpaRepository<UserProfile, Long> {

}
