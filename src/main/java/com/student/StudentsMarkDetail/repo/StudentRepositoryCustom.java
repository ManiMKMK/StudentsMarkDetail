package com.student.StudentsMarkDetail.repo;

import com.student.StudentsMarkDetail.entity.Students;

public interface StudentRepositoryCustom {
        void updateByRollNo(String rollNo, Students students);
        void deleteStudentByRollNo(String rollNo);

}
