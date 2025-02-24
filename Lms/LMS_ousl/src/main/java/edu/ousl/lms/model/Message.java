package edu.ousl.lms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@ToString
public class Message {

    private String msg;
    private long senderId;
    private long reciverId;
    private LocalDate date;
    private LocalTime time;

    private Long chatId;
}
