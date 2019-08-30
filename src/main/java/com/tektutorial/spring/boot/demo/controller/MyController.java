package com.tektutorial.spring.boot.demo.controller;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.tektutorial.spring.boot.demo.bean.Student;
import com.tektutorial.spring.boot.demo.service.StudentService;

@RestController
public class MyController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${HOSTNAME:not_set}")
	private String hostName;

	private StudentService studentService;

	@Autowired
	public MyController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping(value = "/student")
	public List<Student> getStudent(@RequestParam(required = false) Integer studentId) {
		return studentService.getStudent(studentId);
	}

	@PostMapping("/student")
	public DeferredResult<ResponseEntity<?>> addStudent() {
		logger.info("Request received");
		DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
//
//		CompletableFuture.supplyAsync(studentService::addStudent)
//				.whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));

		deferredResult.onCompletion(() -> deferredResult
				.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout.")));
		/**
		 * Section to simulate slow running thread blocking process
		 */
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		forkJoinPool.submit(() -> {
			studentService.addStudent();
			deferredResult.setResult(ResponseEntity.ok("Student Added"));
		});

		logger.info("Servlet thread released");
		return deferredResult;
	}

//    @PostMapping("/student")
//    public String addStudent()  {
//    	logger.info("Request received");
//    	String result = studentService.addStudent();
//    	logger.info("Servlet thread released");
//        return "Student Added";
//    }

	@GetMapping("/hello")
	public String hello() {
		return hostName;
	}
}
