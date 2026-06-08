package com.example.demo.repository.palestra;

import com.example.demo.entity.palestra.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    // Restituire tutti i corsi tenuti da un trainer cercando per nome.
    @Query(
            """
                    SELECT c
                    FROM Course c
                    WHERE LOWER(c.trainer.firstname) = LOWER(:trainerFirstname)
                    """
    )
    List<Course> findCoursesByTrainerFirstname(@Param("trainerName") String trainerFirstname);
}
