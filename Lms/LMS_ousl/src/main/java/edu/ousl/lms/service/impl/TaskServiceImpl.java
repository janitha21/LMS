package edu.ousl.lms.service.impl;

import edu.ousl.lms.entity.ContentEntity;
import edu.ousl.lms.entity.TaskEntity;
import edu.ousl.lms.model.Task;
import edu.ousl.lms.repository.ContentRepository;
import edu.ousl.lms.repository.TaskRepository;
import edu.ousl.lms.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ContentRepository contentRepository;

    @Override
    public void addTask(Task task, long contentId) {

        Optional<ContentEntity> content = contentRepository.findById(contentId);

        TaskEntity taskEntity=new TaskEntity();

        taskEntity.setTaskAddedDate(LocalDateTime.now());

        taskEntity.setTaskType(task.getTaskType());
        taskEntity.setDescription(task.getDescription());
        taskEntity.setLink(task.getLink());
        taskEntity.setDueDate(task.getDueDate());
        taskEntity.setLinkDescription(task.getLinkDescription());
        taskEntity.setOthers(task.getOthers());

        taskEntity.setContent(content.get());


        taskRepository.save(taskEntity);

    }

    @Override
    public List<TaskEntity> getTasksByContent(long contentId) {

        Optional<ContentEntity> content = contentRepository.findById(contentId);


        return taskRepository.findAllByContent(content);




    }
}
