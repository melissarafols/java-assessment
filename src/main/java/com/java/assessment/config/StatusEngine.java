package com.java.assessment.config;

import com.java.assessment.services.UserTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
@Slf4j
public class StatusEngine {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    UserTaskService userTaskService;

    @Scheduled(fixedRate = 5000L)
    public void scheduleTaskAtFixRate() {
        log.info("==========Execute : update pending status to done============");
        userTaskService.updatePendingToDone();
        log.info("Time : " + dateTimeFormatter.format(LocalDateTime.now()));
    }
}
