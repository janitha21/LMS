package edu.ousl.lms.repository;

import edu.ousl.lms.entity.ContentEntity;
import edu.ousl.lms.entity.MentorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<ContentEntity,Long> {
}
