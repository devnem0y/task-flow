package com.devnem0y.task_flow.task;

import java.util.BitSet;

public class TaskSpecifications {
    public static BitSet inProject(Long projectId) {
        return BitSet.valueOf(new long[]{projectId});
    }

    public static BitSet withStatus(TaskStatus status) {
        return null;
    }

    public static Object withPriority(Priority priority) {
        return null;
    }
}
