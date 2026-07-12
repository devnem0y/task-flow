package com.devnem0y.task_flow.task;

import jakarta.validation.constraints.NotNull;

record ChangeStatusRequest(
        @NotNull(message = "Статус обязателен")
        TaskStatus status
) {}
