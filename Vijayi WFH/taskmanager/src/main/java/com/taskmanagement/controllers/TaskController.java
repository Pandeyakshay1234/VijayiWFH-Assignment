package com.taskmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import com.taskmanagement.dto.TaskDto;
import com.taskmanagement.entity.Task;
import com.taskmanagement.services.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/task")

public class TaskController {
	
	@Autowired
	
    private TaskService taskService;
	
	@PostMapping
    public Task createTask(@Valid @RequestBody TaskDto taskDTO) {
		
        return taskService.createTask(taskDTO);
    }

	@PutMapping("/{id}")
	
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody TaskDto taskDTO) {
        return taskService.updateTask(id, taskDTO);
    }
	
	 @DeleteMapping("/{id}")
	 
	    public void  deleteTask(@PathVariable Long id) {
		 
	        taskService.deleteTask(id);
	    }
	 
	 @GetMapping("/{id}")
	 
	    public Task getTaskById(@PathVariable Long id) {
		 
	        return taskService.getTaskById(id);
	        
	    }
	 
	 @GetMapping
	 
	 public Page<Task> getAllTasks(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "11") int size)
	   {
		 	 
	        Pageable pageable = PageRequest.of(page, size);
	        return taskService.getAllTasks(pageable);
	    }
	 

}
