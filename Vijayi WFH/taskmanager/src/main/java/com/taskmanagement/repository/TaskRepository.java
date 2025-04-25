package com.taskmanagement.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagement.entity.Task;

@Repository
public interface TaskRepository  extends  JpaRepository<Task , Long> {

	Optional<Task> findByTitleAndExpectedEndDateTimeAndCreatedById(String title , LocalDateTime endTime , Long createdById);

    Page<Task> findAllByIsDeleteFalse(Pageable pageable);
	
}
