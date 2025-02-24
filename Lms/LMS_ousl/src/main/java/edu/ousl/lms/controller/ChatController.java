package edu.ousl.lms.controller;

import edu.ousl.lms.entity.ChatEntity;
import edu.ousl.lms.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public List<ChatEntity> getChats(@RequestParam Long userId){

        return chatService.getChats(userId);


    }


}
