package edu.ousl.lms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class Task {

    private String taskType;
    private String description;
    private LocalDateTime dueDate;
    private String linkDescription;
    private String link;
    private String others;

}
