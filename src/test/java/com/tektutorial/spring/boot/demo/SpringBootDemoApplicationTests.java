package com.tektutorial.spring.boot.demo;

import com.tektutorial.spring.boot.demo.controller.MyController;
import com.tektutorial.spring.boot.demo.service.StudentService;
import org.awaitility.Duration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.awaitility.Awaitility.await;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MyController.class)
public class SpringBootDemoApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StudentService studentService;

    final static String ROOT_URI = "http://localhost:8082";

    @Test
    public void addStudentTest() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .post("/student")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

       /* Thread.sleep(3000);
        Assert.assertTrue(studentService.isStudentActive());*/

        await()
            .atLeast(Duration.ONE_HUNDRED_MILLISECONDS)
            .atMost(Duration.FIVE_SECONDS)
            .with()
            .pollInterval(Duration.ONE_HUNDRED_MILLISECONDS)
            .until(studentService::isStudentActive);
    }

}
