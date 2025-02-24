package edu.ousl.lms.repository;

import edu.ousl.lms.entity.ContentEntity;
import edu.ousl.lms.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

    List<TaskEntity> findAllByContent(Optional<ContentEntity> content);



}
