package edu.ousl.lms.service.impl;

import edu.ousl.lms.entity.StudentEntity;
import edu.ousl.lms.entity.SubjectEntity;
import edu.ousl.lms.repository.StudentRepository;
import edu.ousl.lms.repository.SubjectRepository;
import edu.ousl.lms.service.StudentSubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
public class StudentSubjectServiceImpl implements StudentSubjectService {

    private StudentRepository studentRepository;
    private SubjectRepository subjectRepository;

    @Override
    public void addSubjectForStudent(String studentEmail, Set<String> subjectIds) {

        StudentEntity student= studentRepository.findByStudentEmail(studentEmail);
        Set<SubjectEntity> subjects = subjectRepository.findAllBySubjectIds(subjectIds);

        Set<SubjectEntity> savedSubjects = student.getSubjects();
        savedSubjects.addAll(subjects);




        studentRepository.save(student);

    }
}
