package com.example.tracking.controller;

import com.example.tracking.dao.CreateWorkoutRequest;
import com.example.tracking.model.AppUser;
import com.example.tracking.model.Workout;
import com.example.tracking.service.AppUserService;
import com.example.tracking.service.WorkoutsService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkoutsController {

    private final AppUserService userService;
    private final WorkoutsService workoutsService;

    public WorkoutsController(final AppUserService userService, WorkoutsService workoutsService) {
        this.workoutsService = workoutsService;
        this.userService = userService;
    }

    @PostMapping("/add-workout")
    public void createWorkout(
            Authentication auth,
            @RequestBody CreateWorkoutRequest body
    ) {
        final AppUser user = loadUser(auth);
        workoutsService.createWorkout(user, body);
    }


    @GetMapping("/list-all-workouts")
    public List<Workout> listAllWorkouts(
            Authentication auth
    ) {
        final AppUser user = loadUser(auth);
        return workoutsService.findAllWorkouts(user);
    }

    private AppUser loadUser(Authentication auth) {
        return userService.loadUserByUsername(auth.getName());
    }


}
