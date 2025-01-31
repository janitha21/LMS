package edu.ousl.lms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Table(name = "mentor")
@Entity
public class MentorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long mentorId;

    private String mentorName;
    private String mentorEmail;
    private String mentorPassword;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SubjectEntity> subjects; // Foreign key mapping
}
