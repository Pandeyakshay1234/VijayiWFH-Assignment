package com.taskmanagement.serviceimplementations;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.taskmanagement.dto.TaskDto;
import com.taskmanagement.entity.Task;
import com.taskmanagement.entity.User;
import com.taskmanagement.execptions.ResourceNotFoundException;
import com.taskmanagement.repository.TaskRepository;
import com.taskmanagement.repository.UserRepository;
import com.taskmanagement.services.TaskService;

@Service

public class TaskServiceImplementation implements TaskService {

	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public Task createTask(TaskDto taskDto) {
		
		Optional<Task> existingtask = taskRepo.findByTitleAndExpectedEndDateTimeAndCreatedById(taskDto.getTitle(),taskDto.getExpectedEndDateTime(), taskDto.getCreatedById());
		
		if(existingtask.isPresent()) {
			throw new IllegalArgumentException("can not have same title and same end datetime for the same user ");
		}
		
		User createdBy = null;
		try {
			createdBy = userRepo.findById(taskDto.getCreatedById())
			        .orElseThrow(() -> new ResourceNotFoundException("CreatedBy user not found"));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
//		User createdBy = userRepo.findById(taskDto.getCreatedById())
//				         .orElseThrow(()-> new ResourceNotFoundException("created user not found"));
				          
				         
		User assingTo = null;
		try {
			 assingTo =  userRepo.findById(taskDto.getAssignedToId())
					       .orElseThrow(() -> new ResourceNotFoundException("assign to user not found"));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		Task task = new Task();
		task.setCreatedBy(createdBy);
		task.setAssignedTo(assingTo);
		task.setTitle(taskDto.getTitle());
		task.setDescription(taskDto.getDescription());
		task.setExpectedEndDateTime(taskDto.getExpectedEndDateTime());
		task.setExpectedStartDateTime(taskDto.getExpectedEndDateTime());
		task.setStatus(taskDto.getStatus());
		
		
		return taskRepo.save(task);
	}

	
	@Override
	public Task updateTask(Long id, TaskDto Dto) {
		
		Task task = null;
		
		try {
			task = taskRepo.findById( id)
					   .orElseThrow(() -> new ResourceNotFoundException("task not found"));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User CreatedBy = null;
		try {
			CreatedBy = userRepo.findById(Dto.getCreatedById())
					          .orElseThrow(()-> new ResourceNotFoundException("user not found"));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User AssignTo = null;
		try {
			AssignTo = userRepo.findById(Dto.getAssignedToId())
					        .orElseThrow(()-> new ResourceNotFoundException("assigned user not found"));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
							
		task.setTitle(Dto.getTitle());
        task.setDescription(Dto.getDescription());
        task.setExpectedStartDateTime(Dto.getExpectedEndDateTime());
        task.setExpectedEndDateTime(Dto.getExpectedEndDateTime());
        task.setCreatedBy(CreatedBy);
        task.setAssignedTo(AssignTo);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
		
		return taskRepo.save(task);
	}

	@Override
	public void deleteTask(Long id) {
          Task task = null;
		
		try {
			task = taskRepo.findById( id)
					   .orElseThrow(() -> new ResourceNotFoundException("task not found"));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		task.setIsDelete(true);  // as in  assignment i am soft deleting  task by set true;
		taskRepo.save(task);
	}

	@Override
	public Task getTaskById(Long id) {
		 Task task = new Task();
			
			try {
				task = taskRepo.findById( id)
						   .orElseThrow(() -> new ResourceNotFoundException("task not found"));
			} catch (ResourceNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			return task;
	}

	@Override
	public Page<Task> getAllTasks(Pageable pageable) {

		
		return taskRepo.findAllByIsDeleteFalse(pageable);

}
	
}
