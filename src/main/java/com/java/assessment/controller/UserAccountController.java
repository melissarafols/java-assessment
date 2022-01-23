package com.java.assessment.controller;

import com.java.assessment.dto.MessageResponse;
import com.java.assessment.model.UserProfile;
import com.java.assessment.services.UserProfileService;
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
public class UserAccountController {
    @Autowired
    private UserProfileService userProfileService;

    public UserAccountController(
            UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveNewUser(
            @RequestBody UserProfile userProfile) {
        try {
            log.info("==== Start: Save New User ====");
            UserProfile profile = userProfileService.saveProfile(userProfile);
            log.info("==== End: Save New User ====");
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

    @PutMapping(path = "/user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUser(@PathVariable("userId") String userId,
                                             @RequestBody UserProfile userProfile) {
        try {
            log.info("==== Start: Update User ====");
            userProfile.setId(Long.valueOf(userId));
            UserProfile profile = userProfileService.updateProfile(userProfile);
            log.info("==== End: Update User ====");
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

    @GetMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllUsers() {
        try {
            log.info("==== Start: Get All Users ====");
            List<UserProfile> profiles = userProfileService.getAllUsers();
            log.info("==== End: Get All Users ====");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(profiles);

        } catch (Exception ex) {
            ex.printStackTrace();
            MessageResponse msgResponse = new MessageResponse(400, "fail",
                    "Error was encountered while processing your request", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(msgResponse);
        }
    }

    @GetMapping(path = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllUsersById(@PathVariable("userId") String userId) {
        try {
            log.info("==== Start: Get User by Id ====");
            UserProfile profile = userProfileService.getProfileInfo(Long.valueOf(userId));
            log.info("==== End: Get User by Id ====");
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
}