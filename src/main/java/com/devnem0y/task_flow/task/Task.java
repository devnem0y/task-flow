package com.devnem0y.task_flow.task;

import com.devnem0y.task_flow.project.Project;
import com.devnem0y.task_flow.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.TODO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority = Priority.MEDIUM;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    private Instant deadline;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    protected Task() {}

    public Task(String title, String description, Priority priority, Instant deadline, Project project) {
        this.title = title;
        this.description = description;
        this.priority = priority != null ? priority : Priority.MEDIUM;
        this.deadline = deadline;
        this.project = project;
    }

    public Long getId()           { return id; }
    public String getTitle()      { return title; }
    public String getDescription(){ return description; }
    public TaskStatus getStatus() { return status; }
    public Priority getPriority() { return priority; }
    public Project getProject()   { return project; }
    public User getAssignee()     { return assignee; }
    public Instant getDeadline()  { return deadline; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    public void update(String title, String description, Priority priority, Instant deadline) {
        if (title != null)       this.title = title;
        if (description != null) this.description = description;
        if (priority != null)    this.priority = priority;
        if (deadline != null)    this.deadline = deadline;
    }

    public void assignTo(User user) {
        this.assignee = user;
    }

    public void transitionTo(TaskStatus newStatus) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new IllegalStateException("Нельзя перевести задачу из %s в %s".formatted(this.status, newStatus));
        }
        this.status = newStatus;
    }
}
