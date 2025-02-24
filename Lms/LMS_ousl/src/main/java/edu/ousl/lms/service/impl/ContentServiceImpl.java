package edu.ousl.lms.service.impl;

import edu.ousl.lms.entity.ContentEntity;
import edu.ousl.lms.entity.SubjectEntity;
import edu.ousl.lms.model.Content;
import edu.ousl.lms.repository.ContentRepository;
import edu.ousl.lms.repository.SubjectRepository;
import edu.ousl.lms.service.ContentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public Content addContent(Content content,String subjectId) {

        Optional<SubjectEntity> optionalSubjectEntity = subjectRepository.findById(subjectId);


        ContentEntity contentEntity=new ContentEntity();
        contentEntity.setTitle(content.getTitle());
        contentEntity.setDescription(content.getDescription());

        contentEntity.setSubject(optionalSubjectEntity.get());

        System.out.println(content.getDescription());
        System.out.println(contentEntity.getDescription());
        contentRepository.save(contentEntity);
        return null;


    }

    @Override
    public List<ContentEntity> getContentBySubject(String subjectId) {

        SubjectEntity subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found for ID: " + subjectId));


        return contentRepository.findAllBySubject(subject);




    }
}
