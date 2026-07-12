package com.devnem0y.task_flow.task;

import com.devnem0y.task_flow.user.User;

public record UserSummary(Long id, String displayName, String email) {
    public static UserSummary from(User user) {
        return new UserSummary(user.getId(), user.getDisplayName(), user.getEmail());
    }
}
