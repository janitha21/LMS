package edu.ousl.lms.controller;


import edu.ousl.lms.model.Mentor;
import edu.ousl.lms.service.MentorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/mentor")
public class MentorController {

    private final MentorService mentorService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    void saveMentor(@RequestBody Mentor mentor){

       Mentor response = mentorService.saveMentor(mentor);
    }

}
