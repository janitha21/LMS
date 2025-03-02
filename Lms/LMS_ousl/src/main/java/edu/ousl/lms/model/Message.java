package edu.ousl.lms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class Message {

    private String msg;
    private long senderId;
    private long reciverId;


    private Long chatId;
}
