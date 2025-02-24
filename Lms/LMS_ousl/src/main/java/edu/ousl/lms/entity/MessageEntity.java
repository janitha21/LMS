package edu.ousl.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@Table(name = "message")
@ToString
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long messageId;

    private String msg;
    private long senderId;
    private long reciverId;
    private LocalDate date;
    private LocalTime time;


    @ManyToOne
    @JoinColumn(name = "chatId", nullable = false)
    private ChatEntity chat;




}
