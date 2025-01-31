package edu.ousl.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "content")
public class ContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long contentId;

    private String title;
    private String Description;
    private String link;

    //-------------
    @ManyToOne
    @JoinColumn(name = "subjectId", nullable = false)
    private SubjectEntity subject;

}
