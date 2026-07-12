package com.devnem0y.task_flow.project;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProjectRepository {
    public <T> ScopedValue<T> findById(@NotNull(message = "Проект обязателен") @Positive Long aLong) {
        return null;
    }
}
