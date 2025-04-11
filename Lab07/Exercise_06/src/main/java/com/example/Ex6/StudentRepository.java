package com.example.Ex6;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.age >= ?1")
    List<Student> findByAge(int age);

    @Query("SELECT COUNT(s) FROM Student s WHERE s.ieltsScore = ?1")
    long countByIelts(double ieltsScore);

    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Student> findByName(String name);
}