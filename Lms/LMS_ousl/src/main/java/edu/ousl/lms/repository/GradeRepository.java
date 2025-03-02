package edu.ousl.lms.repository;

import edu.ousl.lms.entity.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<GradeEntity , Long> {
}
