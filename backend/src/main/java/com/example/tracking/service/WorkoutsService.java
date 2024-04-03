package com.example.tracking.service;

import com.example.tracking.dao.CreateWorkoutRequest;
import com.example.tracking.model.AppUser;
import com.example.tracking.model.Exercise;
import com.example.tracking.model.Workout;
import com.example.tracking.repository.AppUserRepository;
import com.example.tracking.repository.ExerciseRepository;
import com.example.tracking.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WorkoutsService {

    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    public WorkoutsService(ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository) {
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> findAllWorkouts(AppUser user) {
        return user.getWorkouts();
    }

    @Transactional
    public void createWorkout(AppUser user, CreateWorkoutRequest body) {
        final String name = body.name();
        final Long[] ids = body.ids();

        final List<Exercise> exercises = getExercises(ids);

        Workout workout = new Workout(name, exercises);

        user.addWorkout(workout);
        workout.setAppUser(user);

        workoutRepository.save(workout);
    }

    private List<Exercise> getExercises(final Long[] ids) {
        return Arrays.stream(ids)
                .map(id ->
                        exerciseRepository
                                .findById(id)
                                .orElseThrow(() -> new IllegalStateException("Unknown id passed"))
                )
                .toList();
    }

}
