package com.example.tracking.repository;

import com.example.tracking.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
