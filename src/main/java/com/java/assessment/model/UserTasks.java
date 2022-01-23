package com.java.assessment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="TBL_TASKS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "task_id", scope = UserProfile.class)
public class UserTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_tasks_seq")
    @SequenceGenerator(name = "tbl_tasks_seq", allocationSize = 1)
    @JsonProperty("task_id")
    @Column(name="task_id")
    private Long taskId;

    @Column(name="user_id")
    private Long userId;

    @JsonProperty("name")
    @Column(name="name")
    private String name;

    @JsonProperty("description")
    @Column(name="description")
    private String description;

    @JsonProperty("status")
    @Column(name="status")
    private String status;

    @JsonProperty("date_time")
    @Column(name="date_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateTime;
}
