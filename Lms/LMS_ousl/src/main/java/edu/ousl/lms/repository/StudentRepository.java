package edu.ousl.lms.repository;

import edu.ousl.lms.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity,Long> {

    StudentEntity findByStudentEmail(String email);
}
