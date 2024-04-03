package com.example.tracking.controller;

import com.example.tracking.model.ExerciseType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UtilController {

    @GetMapping("/list-types")
    public List<String> listAvailableTypes() {
        return Arrays.stream(ExerciseType.values()).map(Object::toString).toList();
    }

}
