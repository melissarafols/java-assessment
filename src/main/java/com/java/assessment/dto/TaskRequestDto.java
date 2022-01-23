package com.java.assessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDto {

    @JsonProperty("name")
	private String name;

    @JsonProperty("description")
	private String description;

    @JsonProperty("date_time")
	private String dateTime;

}
