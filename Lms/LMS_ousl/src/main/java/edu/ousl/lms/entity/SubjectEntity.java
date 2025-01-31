package edu.ousl.lms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@Table(name = "subject")
public class SubjectEntity {

    @Id
    private String subjectId;

    private String subjectName;
    private String subjectDescription;
    private String subjectIcon;

    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private Set<StudentEntity> students;

    @ManyToOne
    @JoinColumn(name = "mentorId", nullable = false)
    private MentorEntity mentor;


    //------------------
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ContentEntity> content; // Foreign key mapping



}
