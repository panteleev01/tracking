package com.example.tracking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "workout"
)
public class Workout {

    @Id
    @Column(name = "workout_id")
    @SequenceGenerator(
            name = "workout_sequence",
            sequenceName = "workout_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "workout_sequence"
    )
    private Long id;

    @Column(
            name = "workout_name",
            nullable = false
    )
    private String name;


    @ManyToMany
    @JoinTable(
            name = "join_table",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    public List<Exercise> exercises = new ArrayList<>();

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER) // todo check difference
    @JsonIgnore
    public AppUser appUser;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Workout() {
    }

    public Long getId() {
        return id;
    }

    public Workout(final String name, final List<Exercise> exercises) {
        this.name = name;
        this.exercises = exercises;

        for (var e: exercises) {
            e.workouts.add(this);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
