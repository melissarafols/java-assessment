package com.java.assessment.repository;

import com.java.assessment.model.UserTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface TaskRepository
        extends JpaRepository<UserTasks, Long> {

    @Transactional
    @Modifying
    @Query(name = "UserTask.updatePendingToDone", nativeQuery = true)
    void updatePendingToDone(@Param("dateToday") String dateToday);

}
