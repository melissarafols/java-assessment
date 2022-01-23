package com.java.assessment.services.impl;

import com.java.assessment.error.RecordNotFoundException;
import com.java.assessment.model.UserProfileTasks;
import com.java.assessment.model.UserTasks;
import com.java.assessment.repository.TaskRepository;
import com.java.assessment.repository.UserProfileTasksRepository;
import com.java.assessment.services.UserTaskService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserTaskServiceImpl implements UserTaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserProfileTasksRepository userProfileTasksRepository;

    @Override
    public UserTasks saveNewUserTask(UserTasks task) {
        task.setStatus("PENDING");
        task = taskRepository.save(task);
        return task;
    }

    @Override
    public UserTasks updateUserTasks(UserTasks task) {
        Long id = Long.valueOf(task.getTaskId());
        Optional<UserTasks> userTasks = taskRepository.findById(id);


        if (userTasks.isPresent()) {
            if (StringUtils.isBlank(task.getName())) {
                task.setName(userTasks.get().getName());
            }

            if (StringUtils.isBlank(task.getDescription())) {
                task.setDescription(userTasks.get().getDescription());
            }

            if (task.getDateTime() == null) {
                task.setDateTime(userTasks.get().getDateTime());
            }

            task.setStatus(userTasks.get().getStatus());

            task = taskRepository.save(task);

        }
        return task;
    }

    @Override
    public boolean deteleUserTask(Long taskId) throws RecordNotFoundException {
        Optional<UserTasks> task = taskRepository.findById(taskId);

        if (task.isPresent()) {
            taskRepository.deleteById(taskId);
            return true;
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    @Override
    public UserTasks getUserTaskById(Long taskId) throws RecordNotFoundException {
        Optional<UserTasks> task = taskRepository.findById(taskId);

        if (task.isPresent()) {
            return task.get();
        } else {
            throw new RecordNotFoundException("No task record exist for given id");
        }
    }

    @Override
    public List<UserTasks> getTasksByUser(Long userId) throws RecordNotFoundException {
        Optional<UserProfileTasks> task = userProfileTasksRepository.findById(userId);

        if (task.isPresent()) {
            return task.get().getTasks();
        } else {
            throw new RecordNotFoundException("No task record exist for given user id");
        }
    }

    @Override
    public void updatePendingToDone() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        taskRepository.updatePendingToDone(format.format(new Date()));
    }
}
