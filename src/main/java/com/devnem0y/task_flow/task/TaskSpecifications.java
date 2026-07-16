package com.devnem0y.task_flow.task;

import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {

    public static Specification<Task> inProject(Long projectId) {
        return (root, query, cb) -> {
            if (projectId == null) return null;
            return cb.equal(root.get("project").get("id"), projectId);
        };
    }

    public static Specification<Task> withStatus(TaskStatus status) {
        return (root, query, cb) -> {
            if (status == null) return null;
            return cb.equal(root.get("status"), status);
        };
    }

    public static Specification<Task> withPriority(Priority priority) {
        return (root, query, cb) -> {
            if (priority == null) return null;
            return cb.equal(root.get("priority"), priority);
        };
    }
}
