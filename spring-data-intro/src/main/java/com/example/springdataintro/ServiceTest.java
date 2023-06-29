package com.example.springdataintro;

import org.springframework.stereotype.Service;

@Service
public class ServiceTest {

    public final TestRepository testRepository;


    public ServiceTest(TestRepository testRepository) {

        this.testRepository = testRepository;
    }

    public void checkTest() {

    }
}
