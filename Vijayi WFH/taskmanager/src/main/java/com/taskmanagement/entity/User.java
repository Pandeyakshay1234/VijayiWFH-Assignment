package com.taskmanagement.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    
    private String lastName;

    @NotBlank(message = "Timezone is mandatory")
    private String timezone;

    private boolean isActive;

    
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Task> tasksCreated;

    
    @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.ALL)
    private List<Task> tasksAssigned;


    
    public User(Long id, @NotBlank(message = "First Name is mandatory") String firstName, String lastName,
			@NotBlank(message = "Timezone is mandatory") String timezone, boolean isActive, List<Task> tasksCreated,
			List<Task> tasksAssigned) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.timezone = timezone;
		this.isActive = isActive;
		this.tasksCreated = tasksCreated;
		this.tasksAssigned = tasksAssigned;
	}


	public User() {
		super();
	}
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getTimezone() {
		return timezone;
	}


	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public List<Task> getTasksCreated() {
		return tasksCreated;
	}


	public void setTasksCreated(List<Task> tasksCreated) {
		this.tasksCreated = tasksCreated;
	}


	public List<Task> getTasksAssigned() {
		return tasksAssigned;
	}


	public void setTasksAssigned(List<Task> tasksAssigned) {
		this.tasksAssigned = tasksAssigned;
	}


	

    
}
