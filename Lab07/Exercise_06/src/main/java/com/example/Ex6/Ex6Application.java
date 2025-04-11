package com.example.Ex6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootApplication
public class Ex6Application {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentRepositoryWithPaging studentRepositoryWithPaging;

	public static void main(String[] args) {
		SpringApplication.run(Ex6Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			studentRepository.save(new Student(null, "Nguyen Van A", 20, "a@example.com", 6.5));
			studentRepository.save(new Student(null, "Tran Thi B", 21, "b@example.com", 7.0));
			studentRepository.save(new Student(null, "Le Van C", 22, "c@example.com", 7.5));
			studentRepository.save(new Student(null, "Nguyen Van D", 21, "d@example.com", 6.0));
			studentRepository.save(new Student(null, "Tran Thi E", 23, "e@example.com", 8.0));
			studentRepository.save(new Student(null, "Le Van F", 24, "f@example.com", 5.5));
			studentRepository.save(new Student(null, "Nguyen Van G", 20, "g@example.com", 7.5));
			studentRepository.save(new Student(null, "Tran Thi H", 25, "h@example.com", 6.8));
			studentRepository.save(new Student(null, "Le Van I", 22, "i@example.com", 7.3));
			studentRepository.save(new Student(null, "Nguyen Van J", 24, "j@example.com", 6.2));
			studentRepository.save(new Student(null, "Tran Thi K", 20, "k@example.com", 8.5));
			studentRepository.save(new Student(null, "Le Van L", 23, "l@example.com", 7.8));

			List<Student> sortedStudents = studentRepositoryWithPaging.findAllSorted(PageRequest.of(0, 10));
			System.out.println("Danh sách sinh viên sắp xếp:");
			sortedStudents.forEach(student -> System.out.println(student));

			List<Student> students456 = studentRepositoryWithPaging.findStudentsByIndexes(3, 6);
			System.out.println("Sinh viên từ 4 đến 6:");
			students456.forEach(student -> System.out.println(student));
		};
	}
}
