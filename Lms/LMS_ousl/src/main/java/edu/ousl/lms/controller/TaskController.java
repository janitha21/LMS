package edu.ousl.lms.controller;

import edu.ousl.lms.entity.TaskEntity;
import edu.ousl.lms.model.Task;
import edu.ousl.lms.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/task")
@RestController
public class TaskController {

    private final TaskService taskService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    void addTask(@RequestBody Task task,
                 @RequestParam long contentId){

       taskService.addTask(task,contentId);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get")
    List<TaskEntity> getTasksByContent(@RequestParam Long contentId){

        return taskService.getTasksByContent(contentId);
    }

}
