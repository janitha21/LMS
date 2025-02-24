package edu.ousl.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String taskType;
    private String description;
    private LocalDateTime taskAddedDate;
    private LocalDateTime dueDate;
    private String linkDescription;
    private String link;
    private String others;

    @ManyToOne
    @JoinColumn(name = "contentId", nullable = false)
    private ContentEntity content;

}



