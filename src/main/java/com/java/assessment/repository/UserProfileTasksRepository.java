package com.java.assessment.repository;


import com.java.assessment.model.UserProfileTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileTasksRepository
        extends JpaRepository<UserProfileTasks, Long> {

}
