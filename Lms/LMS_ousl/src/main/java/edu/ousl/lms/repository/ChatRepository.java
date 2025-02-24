package edu.ousl.lms.repository;

import edu.ousl.lms.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity,Long> {
    List<ChatEntity> findAllByStudentStudentId(Long userId);

   // List<ChatEntity> findAllByMentorId();
}
