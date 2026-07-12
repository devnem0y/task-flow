package com.devnem0y.task_flow.common.exception;

public class ProjectNotFoundException extends ResourceNotFoundException {
    public ProjectNotFoundException(Long id) {
        super("Проект с id=%d не найден".formatted(id));
    }
}
