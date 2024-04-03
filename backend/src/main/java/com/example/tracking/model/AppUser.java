package com.example.tracking.model;

import jakarta.persistence.*;
import org.hibernate.jdbc.Work;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(
        name = "app_user",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_username_unique", columnNames = "app_user_name")
        }
)
public class AppUser implements UserDetails {

    @Id
    @Column(name = "user_id")
    @SequenceGenerator(
            name = "app_user_sequence",
            sequenceName = "app_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_sequence"
    )
    private Long id;

    @Column(
            name = "app_user_name",
            nullable = false
    )
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;


    @OneToMany(
            mappedBy = "appUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Exercise> exercises = new ArrayList<>();

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void addExercise(final Exercise e) {
        e.setAppUser(this);
        exercises.add(e);
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }


    @OneToMany(
            mappedBy = "appUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Workout> workouts = new ArrayList<>();

    public void addWorkout(final Workout w) {
        w.setAppUser(this);
        workouts.add(w);
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public AppUser() {

    }

    public AppUser(String username, String password, AppUserRole appUserRole) {
        this.username = username;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority sga =
                new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(sga);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
