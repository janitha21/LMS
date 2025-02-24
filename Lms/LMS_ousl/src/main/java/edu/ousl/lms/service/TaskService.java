package edu.ousl.lms.service;

import edu.ousl.lms.entity.TaskEntity;
import edu.ousl.lms.model.Task;

import java.util.List;

public interface TaskService {
    void addTask(Task task, long contentId);

    List<TaskEntity> getTasksByContent(long contentId);
}
