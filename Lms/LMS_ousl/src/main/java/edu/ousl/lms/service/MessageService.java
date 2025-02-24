package edu.ousl.lms.service;

import edu.ousl.lms.entity.MessageEntity;
import edu.ousl.lms.model.Message;

public interface MessageService {
    MessageEntity sendMessage(Message message);

}
