package com.sample.LambdaWebAdapterSample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("")
public class SampleController {

    private final SampleRepository sampleRepository;

    public SampleController(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @GetMapping("/{id}")
    public String sample(@PathVariable int id, Model model) {
        Map<String, Object> map = sampleRepository.findById(id);
        model.addAttribute("name", map.get("name"));
        return "sample";
    }

}
