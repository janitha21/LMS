package edu.ousl.lms.controller;

import edu.ousl.lms.entity.ChatEntity;
import edu.ousl.lms.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @CrossOrigin
    @GetMapping("/get-chats")
    public List<ChatEntity> getChats(@RequestParam Long userId){

        return chatService.getChats(userId);


    }


}
