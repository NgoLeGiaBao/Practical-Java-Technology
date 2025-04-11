package com.example.Ex4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Ex4Application {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Ex4Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			Student student1 = new Student(null, "Nguyen Hoang Anh", 20, "ha1@gmail.com", 6.5);
			Student student2 = new Student(null, "Nguyen Hoang Anh 1", 21, "ha2@gmail.com", 7.0);
			Student student3 = new Student(null, "Nguyen Hoang Anh 2", 22, "ha3@gmail.com", 7.5);
			studentRepository.save(student1);
			studentRepository.save(student2);
			studentRepository.save(student3);

			List<Student> studentsAbove21 = studentRepository.findByAgeGreaterThanEqual(21);
			System.out.println("Danh sách sinh viên từ 21 tuổi trở lên:");
			studentsAbove21.forEach(student -> System.out.println(student));

			long countIelts7 = studentRepository.countByIeltsScore(7.0);
			System.out.println("Số sinh viên có điểm IELTS bằng 7.0: " + countIelts7);

			List<Student> studentsWithNameContainingNguyen = studentRepository.findByNameContainingIgnoreCase("Nguyen");
			System.out.println("Danh sách sinh viên có tên chứa 'Nguyen':");
			studentsWithNameContainingNguyen.forEach(student -> System.out.println(student));
		};
	}
}
