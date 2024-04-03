package com.example.tracking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.jdbc.Work;

import java.util.List;

@Entity
@Table(
        name = "exercise"
)
public class Exercise {

    @Id
    @SequenceGenerator(
            name = "exercise_sequence",
            sequenceName = "exercise_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "exercise_sequence"
    )
    @Column(
            name = "exercise_id"
    )
    private Long id;

    @Column(
            name = "exercise_name",
            nullable = false
    )
    private String name;

    @Column(
            name = "description"
    )
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "type",
            nullable = false
    )
    private ExerciseType type;


    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER) // todo change to lazy
    @JsonIgnore
    private AppUser appUser;

    @ManyToMany(mappedBy = "exercises")
    @JsonIgnore
    public List<Workout> workouts;

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Exercise(String name, String description, ExerciseType type, AppUser appUser) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.appUser = appUser;
    }

    public Exercise() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExerciseType getType() {
        return type;
    }

    public void setType(ExerciseType type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", appUser=" + appUser +
                '}';
    }
}
