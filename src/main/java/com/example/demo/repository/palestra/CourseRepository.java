package com.example.demo.repository.palestra;

import com.example.demo.dto.response.CourseCustomerCountDTO;
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
                    WHERE LOWER(c.trainer.firstname) = LOWER(:trainerName)
                    """
    )
    List<Course> findCoursesByTrainerFirstname(@Param("trainerName") String trainerFirstname);

    // Trovare corsi con durata maggiore di X minuti
    @Query(
            """
                    SELECT c
                    FROM Course c
                    WHERE c.durationMinutes > :minutes
                    """
    )
    List<Course> findCoursesLongerThan(@Param("minutes") Integer minutes);

    // In JPQL devi mettere tutto il percorso perchè non esistono gli import --> DTO creato su misura per questo metodo
    // Contare quanti clienti sono iscritti a ogni corso
    @Query("""
            SELECT new com.example.demo.dto.response.CourseCustomerCountDTO(
                c.name,
                COUNT(customer)
            )
            FROM Course c
            LEFT JOIN c.customers customer
            GROUP BY c.name
            """)
    List<CourseCustomerCountDTO> countCustomersByCourse();

    // Trovare corsi con almeno X iscritti
    // Parti da tutti i Course
    // Prendi tutti i Course
    // Per ogni Course vai nella lista customers
    // Raggruppa per Course
    // Tieni solo quelli con almeno min Customer
    @Query("""
            SELECT c
            FROM Course c
            JOIN c.customers customer
            GROUP BY c
            HAVING COUNT(customer) >= :min
            """)
    List<Course> findCoursesWithAtLeastCustomers(@Param("min") Long min);


}
