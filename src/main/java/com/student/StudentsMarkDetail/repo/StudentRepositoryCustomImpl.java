package com.student.StudentsMarkDetail.repo;

import com.student.StudentsMarkDetail.entity.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepositoryCustomImpl implements StudentRepositoryCustom{
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void updateByRollNo(String rollNo, Students students) {
        Query query = new Query(Criteria.where("rollNo").is(rollNo));
        Update update = new Update()
                .set("name", students.getName())
                .set("mobile", students.getMobile());
        mongoTemplate.updateFirst(query, update, Students.class);
    }

    @Override
    public void deleteStudentByRollNo(String rollNo) {
        Query query = new Query(Criteria.where("rollNo").is(rollNo));
        mongoTemplate.remove(query,Students.class);
    }
}
