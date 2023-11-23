package com.sample.LambdaWebAdapterSample;

import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class SampleController {

    @GetMapping("/users/{id}")
    public ResponseEntity<SampleResponse> sample() {
        var sampleResponse = SampleResponse
                .builder()
                .name("Jack")
                .build();
        return ResponseEntity.ok().body(sampleResponse);
    }

    @Data
    @Builder
    public static class SampleResponse {
        private String name;
    }

}
