package com.student.StudentsMarkDetail.repo;

import com.student.StudentsMarkDetail.entity.Students;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentsRepository extends MongoRepository<Students,String>,StudentRepositoryCustom {
    Students findByRollNo(String rollNo);
}

