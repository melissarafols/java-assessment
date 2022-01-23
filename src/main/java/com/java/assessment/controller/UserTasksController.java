package com.java.assessment.controller;

import com.java.assessment.dto.MessageResponse;
import com.java.assessment.model.UserTasks;
import com.java.assessment.services.UserTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserTasksController {
	@Autowired
	private UserTaskService userTaskService;

	public UserTasksController(
			UserTaskService userTaskService) {
		this.userTaskService = userTaskService;
	}

	@PostMapping(path = "/user/{userId}/task", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveNewUserTask(
			@PathVariable("userId") String userId,
			@RequestBody UserTasks taskRequest) {
		try {
			taskRequest.setUserId(Long.valueOf(userId));
			UserTasks profile = userTaskService.saveNewUserTask(taskRequest);
			return ResponseEntity.status(HttpStatus.OK)
					.body(profile);
		} catch (Exception ex) {
			ex.printStackTrace();
			MessageResponse msgResponse = new MessageResponse(400, "fail",
					"Error was encountered while processing your request", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(msgResponse);
		}
	}

	@PutMapping(path = "/user/{userId}/task/{taskId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateUserTask(
			@PathVariable("userId") String userId,
			@PathVariable("taskId") String taskId,
			@RequestBody UserTasks taskRequest) {
		try {
			taskRequest.setUserId(Long.valueOf(userId));
			taskRequest.setTaskId(Long.valueOf(taskId));
			UserTasks profile = userTaskService.updateUserTasks(taskRequest);
			return ResponseEntity.status(HttpStatus.OK)
					.body(profile);
		} catch (Exception ex) {
			ex.printStackTrace();
			MessageResponse msgResponse = new MessageResponse(400, "fail",
					"Error was encountered while processing your request", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(msgResponse);
		}
	}

	@DeleteMapping(path = "/user/{userId}/task/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteTask(
			@PathVariable("userId") String userId,
			@PathVariable("taskId") String taskId) {
		try {
			boolean deleteTask = userTaskService.deteleUserTask(Long.valueOf(taskId));
			if(deleteTask){
				MessageResponse msgResponse = new MessageResponse(200, "Success",
						"Task id #" + taskId + " for user id #" + userId + " has been deleted.", null);
				return ResponseEntity.status(HttpStatus.OK)
						.body(msgResponse);
			}else{
				MessageResponse msgResponse = new MessageResponse(400, "fail",
						"Error was encountered while processing your request", null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(msgResponse);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			MessageResponse msgResponse = new MessageResponse(400, "fail",
					"Error was encountered while processing your request", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(msgResponse);
		}
	}

	@GetMapping(path = "/user/{userId}/task/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getTaskInfoById(
			@PathVariable("userId") String userId,
			@PathVariable("taskId") String taskId) {
		try {
			UserTasks task = userTaskService.getUserTaskById(Long.valueOf(taskId));
			return ResponseEntity.status(HttpStatus.OK)
					.body(task);

		} catch (Exception ex) {
			ex.printStackTrace();
			MessageResponse msgResponse = new MessageResponse(400, "fail",
					"Error was encountered while processing your request", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(msgResponse);
		}
	}

	@GetMapping(path = "/user/{userId}/task", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getTaskInfoById(
			@PathVariable("userId") String userId) {
		try {
			List<UserTasks> tasks = userTaskService.getTasksByUser(Long.valueOf(userId));
			return ResponseEntity.status(HttpStatus.OK)
					.body(tasks);

		} catch (Exception ex) {
			ex.printStackTrace();
			MessageResponse msgResponse = new MessageResponse(400, "fail",
					"Error was encountered while processing your request", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(msgResponse);
		}
	}
}