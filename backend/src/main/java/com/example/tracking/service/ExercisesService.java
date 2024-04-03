package com.example.tracking.service;

import com.example.tracking.dao.AddExerciseRequest;
import com.example.tracking.model.AppUser;
import com.example.tracking.model.Exercise;
import com.example.tracking.model.ExerciseType;
import com.example.tracking.repository.AppUserRepository;
import com.example.tracking.repository.ExerciseRepository;
import com.example.tracking.repository.WorkoutRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExercisesService {

    private final ExerciseRepository exerciseRepository;


    public ExercisesService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Transactional
    public void addExercise(
            final AppUser user,
            final AddExerciseRequest body
    ) {
        final Exercise e = new Exercise(
            body.name(),
            body.description(),
            convertType(body.type()),
            user
        );
        user.addExercise(e);
        exerciseRepository.save(e);
    }

    public List<Exercise> findAllExercises(AppUser user) {
        return user.getExercises();
    }

    private ExerciseType convertType(final String type) {
        return ExerciseType.valueOf(type.toUpperCase());
    }

}
