package com.example.Ex6;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepositoryWithPaging extends PagingAndSortingRepository<Student, Long> {

    @Query("SELECT s FROM Student s ORDER BY s.age DESC, s.ieltsScore ASC")
    List<Student> findAllSorted(Pageable pageable);

    default List<Student> findStudentsByIndexes(int startIndex, int endIndex) {
        Pageable pageable = PageRequest.of(startIndex / 10, 10);
        List<Student> students = findAllSorted(pageable);
        return students.subList(startIndex % 10, Math.min(endIndex % 10, students.size()));
    }
}