package edu.ousl.lms.service;

import edu.ousl.lms.entity.SubjectEntity;
import edu.ousl.lms.model.Subject;

import java.util.List;

public interface SubjectService {
    List<SubjectEntity> getSubjectsByStudent(Long studentId);
}
