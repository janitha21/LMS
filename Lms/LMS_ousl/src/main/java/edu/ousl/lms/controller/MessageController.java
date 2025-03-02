package edu.ousl.lms.controller;

import edu.ousl.lms.entity.MessageEntity;
import edu.ousl.lms.model.Message;
import edu.ousl.lms.service.MessageService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/sent")
    public void sendMessage(@RequestBody Message message){


       MessageEntity msg = messageService.sendMessage(message);

    }
    @CrossOrigin
    @GetMapping("/get/{chatId}")
    public Page<MessageEntity> getUserMessages(@PathVariable Long chatId,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "20") int size){

         return messageService.getUserMessages(chatId,page,size);

    }


}
