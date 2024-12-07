package com.student.StudentsMarkDetail.controller;

import com.student.StudentsMarkDetail.commonutils.customannotation.ValidParam;
import com.student.StudentsMarkDetail.entity.Students;
import com.student.StudentsMarkDetail.service.StudentsService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/students")
@Validated
public class StudentsController {

    @Autowired
    private StudentsService service;

    @PostMapping("/save")
    private ResponseEntity<Object> saveStudent(@Valid @RequestBody Students students) {
        return service.saveStudent(students);
    }

    @PutMapping("/edit/{rollNo}")
    private ResponseEntity<Object> updateStudent(@Valid @RequestBody Students students, @ValidParam(clazz = Students.class,field = "rollNo") @PathVariable(name = "rollNo") String rollNo) {
        return service.updateStudent(students, rollNo);
    }

    @DeleteMapping("/delete/{rollNo}") // Fixed path variable
    private ResponseEntity<Object> deleteStudent(@ValidParam (clazz = Students.class,field = "rollNo") @PathVariable(name = "rollNo") String rollNo) {
        Students students = new Students();
        students.setRollNo(rollNo);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Students>> violations = validator.validate(students);
        if(!violations.isEmpty()){
            String errorMsg = violations.iterator().next().getMessage();
            return  ResponseEntity.badRequest().body(errorMsg);
        }
        return service.deleteStudent(rollNo);
    }

    @GetMapping("/getAllStudents")
    private ResponseEntity<Iterable<Students>> getAllStudents() {
        return service.listAll();
    }

    @GetMapping("find/{rollNo}")
    private ResponseEntity<Object> findByRollNo(@ValidParam(clazz = Students.class,field = "rollNo") @PathVariable(name = "rollNo") String rollNo) {
        return service.findByRollNo(rollNo);
    }
}
