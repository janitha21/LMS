package edu.ousl.lms.service;

import edu.ousl.lms.entity.MessageEntity;
import edu.ousl.lms.model.Message;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageService {
    MessageEntity sendMessage(Message message);

    Page<MessageEntity> getUserMessages(Long chatId,int page,int size);
}
