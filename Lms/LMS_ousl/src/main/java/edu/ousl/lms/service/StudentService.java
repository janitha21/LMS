package edu.ousl.lms.service;

import edu.ousl.lms.entity.StudentEntity;
import edu.ousl.lms.model.LogUser;
import edu.ousl.lms.model.Student;

public interface StudentService {
    Student saveStudent(Student student);

    StudentEntity checkStudentLog(LogUser logUser);
}
