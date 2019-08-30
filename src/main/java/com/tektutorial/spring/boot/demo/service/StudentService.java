package com.tektutorial.spring.boot.demo.service;

import static java.lang.Thread.sleep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tektutorial.spring.boot.demo.bean.Address;
import com.tektutorial.spring.boot.demo.bean.Name;
import com.tektutorial.spring.boot.demo.bean.Student;

@Service
public class StudentService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final int INIT_DELAY = 2000;
	private Executor executor = Executors.newFixedThreadPool(4);
	private volatile Student student = null;
	private volatile boolean isActive = false;

	private List<Student> students = new ArrayList<>();

	public List<Student> getStudent(Integer studentId) {
		if (studentId != null)
			return Arrays.asList(students.get(--studentId));
		else
			return students;
	}

	public boolean isStudentActive() {
		return isActive;
	}

	public String addStudent() {
		executor.execute(() -> {
			try {
				sleep(INIT_DELAY);
				isActive = true;
				Address address = new Address("first", "second", "third");
				Name name = new Name("first Name", "second name");
				student = new Student(address, name, Boolean.TRUE);
				students.add(student);
				logger.info("Slow task executed");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		return "Student Added";
	}
}
