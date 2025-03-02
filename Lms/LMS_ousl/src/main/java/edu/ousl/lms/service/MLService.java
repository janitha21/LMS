package edu.ousl.lms.service;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

public class MLService {
    private static final String ML_API_URL = "http://localhost:5000/predict";

    public String getPredictedGrade(int examMark, int labMark, int assignmentMark, int attendance) {
        RestTemplate restTemplate = new RestTemplate();

        // Create request body
        Map<String, Integer> requestBody = new HashMap<>();
        requestBody.put("exam_mark", examMark);
        requestBody.put("lab_mark", labMark);
        requestBody.put("assignment_mark", assignmentMark);
        requestBody.put("attendance", attendance);

        // Send POST request
        ResponseEntity<String> response = restTemplate.postForEntity(ML_API_URL, requestBody, String.class);
        System.out.println(response.getBody());
        return response.getBody();  // Return the response from ML API
    }
}

