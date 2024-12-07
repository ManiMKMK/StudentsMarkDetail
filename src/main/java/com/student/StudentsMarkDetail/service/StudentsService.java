package com.student.StudentsMarkDetail.service;

import com.student.StudentsMarkDetail.commonutils.ApiResponse;
import com.student.StudentsMarkDetail.entity.Students;
import com.student.StudentsMarkDetail.repo.StudentsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentsService {
    private static final Logger logger = LoggerFactory.getLogger(StudentsService.class);

    @Autowired
    private StudentsRepository repo;

    // Method to save student and return JSON response
    public ResponseEntity<Object> saveStudent(Students students) {
        try {
            repo.save(students);
            logger.info("New Student Added.");
            return new ResponseEntity<>(new ApiResponse("Student was added successfully..."), HttpStatus.CREATED);
        }
        catch (DuplicateKeyException ex) {
            // Handle duplicate key exception (e.g., duplicate rollNo)
            logger.error("Duplicate roll number detected: {}", students.getRollNo(), ex);
            return new ResponseEntity<>(new ApiResponse("Student with this roll number already exists."), HttpStatus.CONFLICT);

        }
        catch (Exception ex) {
            logger.error("Exception while Save Students. {}", ex.getMessage(), ex);
            return new ResponseEntity<>(new ApiResponse("Student couldn't be added"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Method to update student and return JSON response
    public ResponseEntity<Object> updateStudent(Students students, String rollNo) {
        try {
            repo.updateByRollNo(rollNo, students);
            logger.info("Student Data was Updated.");
            return new ResponseEntity<>(new ApiResponse("Student was updated successfully..."), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Exception while Updating Students. {}", ex.getMessage(), ex);
            return new ResponseEntity<>(new ApiResponse("Student data couldn't be updated..."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Method to delete student and return JSON response
    public ResponseEntity<Object> deleteStudent(String rollNo) {
        try {
            repo.deleteStudentByRollNo(rollNo);
            logger.info("Student was deleted successfully.");
            return new ResponseEntity<>(new ApiResponse("Student was deleted..."), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Exception while deleting Students. {}", ex.getMessage(), ex);
            return new ResponseEntity<>(new ApiResponse("Student data couldn't be deleted..."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Method to list all students
    public ResponseEntity<Iterable<Students>> listAll() {
        Iterable<Students> students = repo.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Method to find student by roll number
    public ResponseEntity<Object> findByRollNo(String rollNo) {
        Students student = repo.findByRollNo(rollNo);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse("Student not found"), HttpStatus.NOT_FOUND);
        }
    }
}
