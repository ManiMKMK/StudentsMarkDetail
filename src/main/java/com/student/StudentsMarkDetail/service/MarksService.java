package com.student.StudentsMarkDetail.service;

import com.student.StudentsMarkDetail.entity.Marks;
import com.student.StudentsMarkDetail.repo.MarksRepository;
import com.student.StudentsMarkDetail.repo.StudentsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MarksService {

    @Autowired
    private MarksRepository marksRepository;
    @Autowired
    private StudentsRepository studentsRepository;
    private static final Logger logger = LoggerFactory.getLogger(MarksService.class);

    public ResponseEntity<String> saveMark(Marks marks) {
        try {
            if(studentsRepository.findByRollNo(marks.getRollNo())!=null){
                marksRepository.save(marks);
                logger.info("Student Marks Added.");
                return new ResponseEntity<>("Student Mark was added successfully...", HttpStatus.CREATED);
            }
            else{
                logger.info("Student is not there");
                return new ResponseEntity<>("Student was not available...", HttpStatus.FAILED_DEPENDENCY);
            }

        } catch (Exception ex) {
            logger.error("Exception while saving student marks: {}", ex.getMessage(), ex);
            return new ResponseEntity<>("Student Mark couldn't be added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateMark(String rollNo, Marks marks) {
        try {
            marksRepository.updateByRollNo(rollNo, marks);
            logger.info("Student Mark was updated.");
            return new ResponseEntity<>("Student Mark was updated successfully...", HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Exception while updating marks: {}", ex.getMessage(), ex);
            return new ResponseEntity<>("Marks data couldn't be updated...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteMark(String rollNo) {
        try {
            marksRepository.deleteByRollNo(rollNo);
            logger.info("Student Mark was deleted successfully.");
            return new ResponseEntity<>("Student Mark was deleted...", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            logger.error("Exception while deleting student's mark: {}", ex.getMessage(), ex);
            return new ResponseEntity<>("Student Mark couldn't be deleted...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Iterable<Marks>> listAll() {
        try {
            Iterable<Marks> marksList = marksRepository.findAll();
            return new ResponseEntity<>(marksList, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Exception while retrieving marks list: {}", ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Marks> findByRollNo(String rollNo) {
        try {
            Marks mark = marksRepository.findByRollNo(rollNo);
            return mark != null ? new ResponseEntity<>(mark, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.error("Exception while finding mark by roll number: {}", ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
