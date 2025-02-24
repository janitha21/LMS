package edu.ousl.lms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Setter
@Table(name = "chat")
@Getter
@ToString
public class ChatEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long chatId;

    @ManyToOne
    @JoinColumn(name = "studentId", nullable = false)
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "mentorId", nullable = false)
    private MentorEntity mentor;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MessageEntity> message; // Foreign key mapping



}
