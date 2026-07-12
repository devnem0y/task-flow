package com.devnem0y.task_flow.task;

public enum TaskStatus {
    TODO,
    IN_PROGRESS,
    IN_REVIEW,
    DONE;

    public boolean canTransitionTo(TaskStatus target) {
        return switch (this) {
            case TODO        -> target == IN_PROGRESS;
            case IN_PROGRESS -> target == IN_REVIEW || target == TODO;
            case IN_REVIEW   -> target == DONE || target == IN_PROGRESS;
            case DONE        -> false;  // завершённую задачу нельзя переоткрыть через статус
        };
    }
}
