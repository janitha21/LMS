package edu.ousl.lms.service;

import java.util.Set;

public interface StudentSubjectService {
    void addSubjectForStudent(String studentEmail, Set<String> subjectIds);
}
