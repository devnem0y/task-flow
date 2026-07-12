package com.devnem0y.task_flow.common.exception;

public class TaskNotFoundException extends ResourceNotFoundException {
    public TaskNotFoundException(Long id) {
        super("Задача с id=%d не найдена".formatted(id));
    }
}
