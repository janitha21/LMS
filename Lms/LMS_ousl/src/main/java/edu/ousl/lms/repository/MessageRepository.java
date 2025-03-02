package edu.ousl.lms.repository;

import edu.ousl.lms.entity.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    Page<MessageEntity> findByChatChatId(Long chatId, Pageable pageable);

}
