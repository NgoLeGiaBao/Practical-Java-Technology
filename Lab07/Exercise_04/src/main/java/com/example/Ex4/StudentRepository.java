package com.example.Ex4;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByAgeGreaterThanEqual(int age);

    long countByIeltsScore(double ieltsScore);

    List<Student> findByNameContainingIgnoreCase(String name);
}

