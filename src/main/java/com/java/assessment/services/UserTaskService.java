package com.java.assessment.services;

import com.java.assessment.error.RecordNotFoundException;
import com.java.assessment.model.UserTasks;

import java.util.List;

public interface UserTaskService {

    UserTasks saveNewUserTask(UserTasks task);
    UserTasks updateUserTasks(UserTasks task);
    boolean deteleUserTask(Long taskId) throws RecordNotFoundException;
    UserTasks getUserTaskById(Long taskId) throws RecordNotFoundException;
    List<UserTasks> getTasksByUser(Long userId) throws RecordNotFoundException;

    void updatePendingToDone();

}
