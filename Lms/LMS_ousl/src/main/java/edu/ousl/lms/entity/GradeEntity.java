package edu.ousl.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
public class GradeEntity {
    @Id
    private long gradeId;

    private int examMark;
    private int labMark;
    private int assignmentMark;
    private int attendance;
    private String grade;

    @ManyToOne
    @JoinColumn(name = "studentId", nullable = false)
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "subjectId", nullable = false)
    private SubjectEntity subject;
}
