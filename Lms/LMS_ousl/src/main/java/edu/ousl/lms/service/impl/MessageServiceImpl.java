package edu.ousl.lms.service.impl;

import edu.ousl.lms.entity.ChatEntity;
import edu.ousl.lms.entity.MessageEntity;
import edu.ousl.lms.entity.StudentEntity;
import edu.ousl.lms.model.Message;
import edu.ousl.lms.repository.ChatRepository;
import edu.ousl.lms.repository.MessageRepository;
import edu.ousl.lms.service.ChatService;
import edu.ousl.lms.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final ChatService chatService;


    @Override
    public MessageEntity sendMessage(Message message) {

       if(message.getChatId()==null){
          //ChatService chatService=new ChatServiceImpl();
          Long chatId = chatService.createChat(message.getSenderId(),message.getReciverId());

          MessageEntity messageEntity=new MessageEntity();
          messageEntity.setMsg(message.getMsg());
          messageEntity.setSenderId(message.getSenderId());
          messageEntity.setReciverId(message.getReciverId());
          messageEntity.setDate(LocalDate.now());
          messageEntity.setTime(LocalTime.now());

           ChatEntity chat=chatRepository.findById(chatId)
                   .orElseThrow(() -> new RuntimeException("Student not found with ID: " + chatId));;

                   messageEntity.setChat(chat);
                   messageRepository.save(messageEntity);
                   return messageEntity;
       }
       else {
           MessageEntity messageEntity = new MessageEntity();
           messageEntity.setMsg(message.getMsg());
           messageEntity.setSenderId(message.getSenderId());
           messageEntity.setReciverId(message.getReciverId());
           messageEntity.setDate(LocalDate.now());
           messageEntity.setTime(LocalTime.now());

           ChatEntity chat=chatRepository.findById(message.getChatId())
                   .orElseThrow(() -> new RuntimeException("Student not found with ID: " + message.getChatId()));;

           messageEntity.setChat(chat);
           messageRepository.save(messageEntity);
           return messageEntity;
       }

    }

    @Override
    public Page<MessageEntity> getUserMessages(Long chatId,int page,int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "messageId"));
        return messageRepository.findByChatChatId(chatId,pageable);

    }
}
