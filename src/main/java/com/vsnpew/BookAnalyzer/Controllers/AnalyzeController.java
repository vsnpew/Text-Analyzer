package com.vsnpew.BookAnalyzer.Controllers;

import com.vsnpew.BookAnalyzer.Services.AnalyzeService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyzeController {


    private final AnalyzeService analyzeService;

    @Autowired
    public AnalyzeController(AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }

    @GetMapping("/books/")
    public String findAll() {
        analyzeService.findAll();
        return "ok";
    }

    @GetMapping("/save/")
    public String saveToPost() {
        return "saved";
    }
}
