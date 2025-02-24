package edu.ousl.lms.service.impl;

import edu.ousl.lms.entity.MentorEntity;
import edu.ousl.lms.entity.SubjectEntity;
import edu.ousl.lms.model.Subject;
import edu.ousl.lms.repository.MentorRepository;
import edu.ousl.lms.repository.SubjectRepository;
import edu.ousl.lms.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final MentorRepository mentorRepository;

    @Override
    public List<SubjectEntity> getSubjectsByStudent(Long studentId) {

       return subjectRepository.findAllByStudentId(studentId);

    }

    @Override
    public List<SubjectEntity> getAllSubjects() {
       return subjectRepository.findAll();

    }

    @Override
    public void addSubjectForMentor(Subject subject, long mentorId) {

        Optional<MentorEntity> mentor = mentorRepository.findById(mentorId);

        SubjectEntity subjectEntity=new SubjectEntity();

        subjectEntity.setSubjectId(subject.getSubjectId());
        subjectEntity.setSubjectName(subject.getSubjectName());
        subjectEntity.setSubjectDescription(subject.getSubjectDescription());
        subjectEntity.setSubjectIcon(subject.getSubjectIcon());

        subjectEntity.setMentor(mentor.get());

        subjectRepository.save(subjectEntity);



    }

    @Override
    public List<SubjectEntity> getSubjectsByMentor(Long mentorId) {
        return subjectRepository.findAllByMentorMentorId(mentorId);
    }
}
