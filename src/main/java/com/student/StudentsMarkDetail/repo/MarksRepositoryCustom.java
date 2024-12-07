package com.student.StudentsMarkDetail.repo;

import com.student.StudentsMarkDetail.entity.Marks;

public interface MarksRepositoryCustom {
    void updateByRollNo(String rollNo, Marks marks);
    void deleteByRollNo(String rollNo);
}
