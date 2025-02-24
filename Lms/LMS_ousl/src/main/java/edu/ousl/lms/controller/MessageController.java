package edu.ousl.lms.controller;

import edu.ousl.lms.entity.MessageEntity;
import edu.ousl.lms.model.Message;
import edu.ousl.lms.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    
    public void sendMessage(@RequestBody Message message){

       MessageEntity msg = messageService.sendMessage(message);

    }
}
