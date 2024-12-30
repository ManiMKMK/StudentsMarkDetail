package com.student.StudentsMarkDetail.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.StudentsMarkDetail.commonutils.ApiResponse;
import com.student.StudentsMarkDetail.commonutils.customannotation.ValidParam;
import com.student.StudentsMarkDetail.entity.Students;
import com.student.StudentsMarkDetail.service.StudentsService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/students")
public class StudentsController {

    @Autowired
    private StudentsService service;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private ResponseEntity<Object> saveStudent(@RequestParam("studentData") String studentData, @RequestParam("profileImg") MultipartFile profileImg) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Students student = objectMapper.readValue(studentData, Students.class);
            if (!profileImg.isEmpty()) {
                student.setProfileImg(profileImg.getBytes());
            }
            return service.saveStudent(student);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse("Failed to save student: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
