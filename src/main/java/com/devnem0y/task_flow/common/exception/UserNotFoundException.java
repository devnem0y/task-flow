package com.devnem0y.task_flow.common.exception;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(Long id) {
        super("Пользователь с id=%d не найден".formatted(id));
    }
}
