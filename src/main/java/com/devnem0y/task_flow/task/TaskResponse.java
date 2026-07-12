package com.devnem0y.task_flow.task;

import java.time.Instant;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        Priority priority,
        Instant createdAt,
        Instant updatedAt,
        Instant deadline,
        UserSummary assignee
) {
    public static TaskResponse from(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getDeadline(),
                task.getAssignee() != null ? UserSummary.from(task.getAssignee()) : null
        );
    }
}
