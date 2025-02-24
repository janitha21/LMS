package edu.ousl.lms.repository;

import edu.ousl.lms.entity.ContentEntity;
import edu.ousl.lms.entity.MentorEntity;
import edu.ousl.lms.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<ContentEntity,Long> {



    List<ContentEntity> findAllBySubject(SubjectEntity subjectId);
}
