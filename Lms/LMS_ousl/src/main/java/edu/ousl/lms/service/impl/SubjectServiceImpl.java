package edu.ousl.lms.service.impl;

import edu.ousl.lms.entity.SubjectEntity;
import edu.ousl.lms.model.Subject;
import edu.ousl.lms.repository.SubjectRepository;
import edu.ousl.lms.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public List<SubjectEntity> getSubjectsByStudent(Long studentId) {

       return subjectRepository.findAllByStudentId(studentId);

    }
}
