package com.taskmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import com.taskmanagement.Enum.TaskStatus;

public class TaskDto {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Status is required")
    private TaskStatus status;

    private LocalDateTime expectedStartDateTime;

    private LocalDateTime expectedEndDateTime;

    @NotNull(message = "Assigned To is required")
    private Long assignedToId;

    @NotNull(message = "Created By is required")
    private Long createdById;

    
    
	public TaskDto(@NotBlank(message = "Title is required") String title,
			@NotBlank(message = "Description is required") String description,
			@NotNull(message = "Status is required") TaskStatus status, LocalDateTime expectedStartDateTime,
			LocalDateTime expectedEndDateTime, @NotNull(message = "Assigned To is required") Long assignedToId,
			@NotNull(message = "Created By is required") Long createdById) {
		super();
		this.title = title;
		this.description = description;
		this.status = status;
		this.expectedStartDateTime = expectedStartDateTime;
		this.expectedEndDateTime = expectedEndDateTime;
		this.assignedToId = assignedToId;
		this.createdById = createdById;
	}
	
	

	public TaskDto() {
		super();
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public LocalDateTime getExpectedStartDateTime() {
		return expectedStartDateTime;
	}

	public void setExpectedStartDateTime(LocalDateTime expectedStartDateTime) {
		this.expectedStartDateTime = expectedStartDateTime;
	}

	public LocalDateTime getExpectedEndDateTime() {
		return expectedEndDateTime;
	}

	public void setExpectedEndDateTime(LocalDateTime expectedEndDateTime) {
		this.expectedEndDateTime = expectedEndDateTime;
	}

	public Long getAssignedToId() {
		return assignedToId;
	}

	public void setAssignedToId(Long assignedToId) {
		this.assignedToId = assignedToId;
	}

	public Long getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}

	
    
    

}
