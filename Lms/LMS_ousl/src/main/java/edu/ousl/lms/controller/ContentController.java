package edu.ousl.lms.controller;


import edu.ousl.lms.entity.ContentEntity;
import edu.ousl.lms.model.Content;
import edu.ousl.lms.service.ContentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/content")
public class ContentController {

    private final ContentService contentService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
     void addContent(@RequestBody Content content,
                     @RequestParam String subjectId)
     {
         System.out.println(content.getDescription());
         System.out.println(content.getTitle());
        Content content1=contentService.addContent(content,subjectId);

     }
     @CrossOrigin(origins = "http://localhost:4200")
     @GetMapping("get-by-id")
     List<ContentEntity> getContentBySubject(@RequestParam String subjectId){

        return  contentService.getContentBySubject(subjectId);


     }






}
