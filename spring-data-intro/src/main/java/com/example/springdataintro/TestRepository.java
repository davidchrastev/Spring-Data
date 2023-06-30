package com.example.springdataintro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
    TestEntity findByEmail(TestEntity testEntity);

    TestEntity findByFirstNameAndEmail(TestEntity testEntity);

}
