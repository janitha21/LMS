package edu.ousl.lms.repository;

import edu.ousl.lms.entity.MentorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<MentorEntity,Long> {
    MentorEntity findByMentorEmail(String email);
}
