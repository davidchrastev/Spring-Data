package com.example.springdataintro;

import org.springframework.stereotype.Service;

@Service
public class ServiceTest {

    public final TestRepository testRepository;


    public ServiceTest(TestRepository testRepository) {

        this.testRepository = testRepository;
    }

    public TestEntity findByEmail(TestEntity testEntity) {
        return testRepository.findByEmail(testEntity);
    }

    public TestEntity findByFirstNameAndEmail(TestEntity testEntity) {
        return testRepository.findByFirstNameAndEmail(testEntity);
    }
}
