package com.devnem0y.task_flow.task;

import jakarta.validation.constraints.*;
import java.time.Instant;

record CreateTaskRequest(

        @NotBlank(message = "Название задачи обязательно")
        @Size(max = 255)
        String title,

        @Size(max = 10000)
        String description,

        @NotNull(message = "Проект обязателен")
        @Positive
        Long projectId,

        Priority priority,

        @Future(message = "Дедлайн должен быть в будущем")
        Instant deadline,

        @Positive
        Long assigneeId
) {}
