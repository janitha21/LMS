package edu.ousl.lms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Grade {
    private int examMark;
    private int labMark;
    private int assignmentMark;
    private int attendance;
    private char grade;
}
