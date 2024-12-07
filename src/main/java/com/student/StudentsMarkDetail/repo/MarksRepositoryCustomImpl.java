package com.student.StudentsMarkDetail.repo;

import com.student.StudentsMarkDetail.entity.Marks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class MarksRepositoryCustomImpl implements MarksRepositoryCustom{
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public void updateByRollNo(String rollNo, Marks marks) {
        Query query = new Query(Criteria.where("rollNo").is(rollNo));
        Update update = new Update()
                .set("M1",marks.getM1())
                .set("M2",marks.getM2())
                .set("M3",marks.getM3());
        mongoTemplate.updateFirst(query,update,Marks.class);
    }

    @Override
    public void deleteByRollNo(String rollNo) {
        Query query = new Query(Criteria.where("rollNo").is(rollNo));
        mongoTemplate.remove(query,Marks.class);
    }
}
