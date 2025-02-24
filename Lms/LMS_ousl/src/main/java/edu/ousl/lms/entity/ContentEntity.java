package edu.ousl.lms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "content")
public class ContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long contentId;

    private String title;
    private String description;

    //-------------
    @ManyToOne
    @JoinColumn(name = "subjectId", nullable = false)
    private SubjectEntity subject;

    //-------
    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TaskEntity> tasks; // Foreign key mapping


}
