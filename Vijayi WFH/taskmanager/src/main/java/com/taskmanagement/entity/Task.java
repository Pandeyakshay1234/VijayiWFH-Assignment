package com.taskmanagement.entity;

import java.time.LocalDateTime;
import com.taskmanagement.Enum.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "task")

public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
    private String title;
	@NotBlank
    private String description;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus status;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private LocalDateTime expectedStartDateTime;
	
	private LocalDateTime expectedEndDateTime;
	
	@ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

	
    @ManyToOne
    @JoinColumn(name = "assigned_to", nullable = false)
    private User assignedTo;

    private Boolean isDelete = false;
    
    
    
    public Task(long id, @NotBlank String title, @NotBlank String description, TaskStatus status,
			LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime expectedStartDateTime,
			LocalDateTime expectedEndDateTime, User createdBy, User assignedTo, Boolean isDelete) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.expectedStartDateTime = expectedStartDateTime;
		this.expectedEndDateTime = expectedEndDateTime;
		this.createdBy = createdBy;
		this.assignedTo = assignedTo;
		this.isDelete = isDelete;
	}

	public Task() {
		super();
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
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

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	

}
