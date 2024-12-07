package com.student.StudentsMarkDetail.repo;

import com.student.StudentsMarkDetail.entity.Marks;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarksRepository extends MongoRepository<Marks,String>,MarksRepositoryCustom {
    Marks findByRollNo(String rollNo);
}
