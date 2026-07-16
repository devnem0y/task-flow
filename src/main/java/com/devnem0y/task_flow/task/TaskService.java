package com.devnem0y.task_flow.task;

import com.devnem0y.task_flow.common.exception.ProjectNotFoundException;
import com.devnem0y.task_flow.common.exception.TaskNotFoundException;
import com.devnem0y.task_flow.common.exception.UserNotFoundException;
import com.devnem0y.task_flow.project.ProjectRepository;
import com.devnem0y.task_flow.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository,
                       ProjectRepository projectRepository,
                       UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    // ── CREATE ─────────────────────────────────────────────────────────────

    public TaskResponse create(CreateTaskRequest request) {
        var project = projectRepository.findById(request.projectId())
                .orElseThrow(() -> new ProjectNotFoundException(request.projectId()));

        var task = new Task(
                request.title(),
                request.description(),
                request.priority(),
                request.deadline(),
                project
        );

        if (request.assigneeId() != null) {
            var assignee = userRepository.findById(request.assigneeId())
                    .orElseThrow(() -> new UserNotFoundException(request.assigneeId()));
            task.assignTo(assignee);
        }

        return TaskResponse.from(taskRepository.save(task));
    }

    // ── READ ───────────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public TaskResponse findById(Long id) {
        return taskRepository.findById(id)
                .map(TaskResponse::from)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public Page<TaskResponse> findByProject(Long projectId,
                                            TaskStatus status,
                                            Priority priority,
                                            Pageable pageable) {
        var spec = TaskSpecifications.inProject(projectId)
                .and(TaskSpecifications.withStatus(status))
                .and(TaskSpecifications.withPriority(priority));

        return taskRepository.findAll(spec, pageable).map(TaskResponse::from);
    }

    // ── UPDATE ─────────────────────────────────────────────────────────────

    public TaskResponse update(Long id, UpdateTaskRequest request) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.update(request.title(), request.description(),
                request.priority(), request.deadline());

        // @Transactional — изменения сохранятся автоматически при коммите
        return TaskResponse.from(task);
    }

    public TaskResponse changeStatus(Long id, TaskStatus newStatus) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.transitionTo(newStatus);
        return TaskResponse.from(task);
    }

    // ── DELETE ─────────────────────────────────────────────────────────────

    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }
}
