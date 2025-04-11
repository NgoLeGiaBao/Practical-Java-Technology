package com.example.Ex2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Ex2Application {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Ex2Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			// Thêm 3 sinh viên vào cơ sở dữ liệu
			Student student1 = new Student(null, "Nguyen Hoang Anh", 20, "ha1@gmail.com", 6.5);
			Student student2 = new Student(null, "Nguyen Hoang Anh 1", 21, "ha2@gmail.com", 7.0);
			Student student3 = new Student(null, "Nguyen Hoang Anh 2", 22, "ha3@gmail.com", 7.5);
			studentRepository.save(student1);
			studentRepository.save(student2);
			studentRepository.save(student3);

			// Đọc danh sách sinh viên và in ra console
			List<Student> students = (List<Student>) studentRepository.findAll();
			System.out.println("Danh sách sinh viên:");
			students.forEach(student -> System.out.println(student));

			// Cập nhật thông tin sinh viên
			if (!students.isEmpty()) {
				Student studentToUpdate = students.get(0);
				studentToUpdate.setName("Nguyen Hoang Em");
				studentToUpdate.setAge(30);
				studentToUpdate.setEmail("EmailUpdated@gmail.com");
				studentRepository.save(studentToUpdate);
				System.out.println("Cập nhật thông tin sinh viên: " + studentToUpdate);
			}

			// Xóa một sinh viên khỏi cơ sở dữ liệu
			if (!students.isEmpty()) {
				Student studentToDelete = students.get(1);
				studentRepository.delete(studentToDelete);

				// In ra danh sách sinh viên còn lại sau khi xóa
				List<Student> remainingStudents = (List<Student>) studentRepository.findAll();
				System.out.println("Danh sách sinh viên còn lại sau khi xóa:");
				remainingStudents.forEach(student -> System.out.println(student));
			}
		};
	}
}
