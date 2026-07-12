package com.devnem0y.task_flow.task;

import jakarta.validation.constraints.Size;
import java.time.Instant;

record UpdateTaskRequest(

        @Size(max = 255)
        String title,

        @Size(max = 10000)
        String description,

        Priority priority,

        Instant deadline
) {}
