package edu.ousl.lms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Student {
    private String studentName;
    private String studentEmail;
    private String contactNumber;
    private String identityNumber;



}
