package com.example.tracking.controller;

import com.example.tracking.dao.AddExerciseRequest;
import com.example.tracking.model.AppUser;
import com.example.tracking.model.Exercise;
import com.example.tracking.service.AppUserService;
import com.example.tracking.service.ExercisesService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExercisesController {

    private final AppUserService userService;
    private final ExercisesService exercisesService;

    public ExercisesController(final AppUserService userService, ExercisesService exercisesService) {
        this.userService = userService;
        this.exercisesService = exercisesService;
    }

    @PostMapping("/add-exercise")
    public void addExercise(
            Authentication auth,
            @RequestBody AddExerciseRequest body) {
        final AppUser user = loadUser(auth);
        exercisesService.addExercise(user, body);
    }

    @GetMapping("/list-all")
    public List<Exercise> listAllExercises(
            Authentication auth
    ) {
        final AppUser user = loadUser(auth);
        return exercisesService.findAllExercises(user);
    }

    private AppUser loadUser(Authentication auth) {
        return userService.loadUserByUsername(auth.getName());
    }

}
