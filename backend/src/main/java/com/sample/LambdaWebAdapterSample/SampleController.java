package com.sample.LambdaWebAdapterSample;

import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:3000")
public class SampleController {

    private final SampleRepository sampleRepository;

    public SampleController(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SampleResponse> sample(@PathVariable int id) {
        Map<String, Object> map = sampleRepository.findById(id);
        var sampleResponse = SampleResponse
                .builder()
                .name(map.get("name"))
                .build();
        return ResponseEntity.ok().body(sampleResponse);
    }

    @Data
    @Builder
    public static class SampleResponse {
        private Object name;
    }

}
