package com.taskmanagement.taskmanager;

//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class TaskControllerTest {
//
//	@Test
//	void contextLoads() {
//	}
//
//
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmanagement.dto.TaskDto;
import com.taskmanagement.entity.Task;
import com.taskmanagement.entity.User;
import com.taskmanagement.Enum.TaskStatus;
import com.taskmanagement.controllers.TaskController;
import com.taskmanagement.services.TaskService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    public void testCreateTask() throws Exception {
        TaskDto dto = new TaskDto(
            "Test Title",
            "Test Description",
            TaskStatus.PENDING,
            LocalDateTime.of(2025, 4, 25, 10, 0),
            LocalDateTime.of(2025, 4, 26, 18, 0),
            1L,
            2L
        );

        Task savedTask = new Task();
        savedTask.setId(100L);
        savedTask.setTitle(dto.getTitle());
        savedTask.setDescription(dto.getDescription());
        savedTask.setStatus(dto.getStatus());
        savedTask.setExpectedStartDateTime(dto.getExpectedStartDateTime());
        savedTask.setExpectedEndDateTime(dto.getExpectedEndDateTime());
        savedTask.setCreatedBy(new User());
        savedTask.setAssignedTo(new User());

        when(taskService.createTask(any(TaskDto.class))).thenReturn(savedTask);

        mockMvc.perform(post("/api/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(100L))
            .andExpect(jsonPath("$.title").value("Test Title"));

        verify(taskService, times(1)).createTask(any(TaskDto.class));
    }

    @Test
    public void testUpdateTask() throws Exception {
        Long taskId = 100L;
        TaskDto dto = new TaskDto(
            "Updated Title",
            "Updated Description",
            TaskStatus.IN_PROGRESS,
            LocalDateTime.of(2025, 4, 25, 10, 0),
            LocalDateTime.of(2025, 4, 27, 18, 0),
            1L,
            2L
        );

        Task updatedTask = new Task();
        updatedTask.setId(taskId);
        updatedTask.setTitle(dto.getTitle());
        updatedTask.setDescription(dto.getDescription());
        updatedTask.setStatus(dto.getStatus());
        updatedTask.setExpectedStartDateTime(dto.getExpectedStartDateTime());
        updatedTask.setExpectedEndDateTime(dto.getExpectedEndDateTime());

        when(taskService.updateTask(eq(taskId), any(TaskDto.class))).thenReturn(updatedTask);

        mockMvc.perform(put("/api/task/{id}", taskId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(taskId))
            .andExpect(jsonPath("$.title").value("Updated Title"));

        verify(taskService, times(1)).updateTask(eq(taskId), any(TaskDto.class));
    }

    @Test
    public void testDeleteTask() throws Exception {
        Long taskId = 100L;

        doNothing().when(taskService).deleteTask(taskId);

        mockMvc.perform(delete("/api/task/{id}", taskId))
            .andExpect(status().isOk());

        verify(taskService, times(1)).deleteTask(taskId);
    }

    @Test
    public void testGetTaskById() throws Exception {
        Long taskId = 100L;
        Task task = new Task();
        task.setId(taskId);
        task.setTitle("Sample Title");
        task.setDescription("Sample Description");
        task.setStatus(TaskStatus.COMPLETED);

        when(taskService.getTaskById(taskId)).thenReturn(task);

        mockMvc.perform(get("/api/task/{id}", taskId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(taskId))
            .andExpect(jsonPath("$.title").value("Sample Title"));

        verify(taskService, times(1)).getTaskById(taskId);
    }

    @Test
    public void testGetAllTasks() throws Exception {
        Task task1 = new Task(); task1.setId(1L); task1.setTitle("T1");
        Task task2 = new Task(); task2.setId(2L); task2.setTitle("T2");
        List<Task> tasks = Arrays.asList(task1, task2);
        Page<Task> page = new PageImpl<>(tasks, PageRequest.of(0, 2), tasks.size());

        when(taskService.getAllTasks(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/task")
                .param("page", "0")
                .param("size", "2"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content.length()").value(2))
            .andExpect(jsonPath("$.content[0].id").value(1))
            .andExpect(jsonPath("$.content[1].id").value(2));

        verify(taskService, times(1)).getAllTasks(any(Pageable.class));
    }
}

