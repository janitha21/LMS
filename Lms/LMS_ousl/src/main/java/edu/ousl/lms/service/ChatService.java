package edu.ousl.lms.service;

import edu.ousl.lms.entity.ChatEntity;

import java.util.List;

public interface ChatService {
    List<ChatEntity> getChats(Long userId);
    Long createChat(Long senderId,Long reciverId);
}
