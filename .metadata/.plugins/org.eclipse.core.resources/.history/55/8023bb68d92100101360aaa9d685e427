package com.taskmanagement.services;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.taskmanagement.dto.TaskDto;
import com.taskmanagement.entity.Task;



public interface TaskService {
	
	Task createTask(TaskDto taskDto);
	Task updateTask(Long id , TaskDto Dto);
	void deleteTask(Long id);
	Task getTaskById(Long id);
	Page<Task> getAllTasks(Pageable pageable);

}
