package com.springboot.exercise;

import com.springboot.exercise.models.Student;
import lombok.Data;
import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@SpringBootApplication
public class ExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExerciseApplication.class, args);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		String dateInString = "7-Jun-2013";
		Student std = new Student();

		std.setName("sein");
		std.setEmail("sein@example.com");
		std.setAddress("Building 1");
		std.setDob(new Date());
		std.setPhone("0912345678");
		std.setTownship("dgsk");
		std.setState("ygn");
//		System.out.println(std.toString());

	}
}
