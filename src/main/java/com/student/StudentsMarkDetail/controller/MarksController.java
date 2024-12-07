package com.student.StudentsMarkDetail.controller;

import com.student.StudentsMarkDetail.commonutils.customannotation.ValidParam;
import com.student.StudentsMarkDetail.entity.Marks;
import com.student.StudentsMarkDetail.service.MarksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/marks")
@Validated
public class MarksController {

    @Autowired
    private MarksService service;

    @PostMapping("/save")
    public ResponseEntity<String> saveMark(@Valid @RequestBody Marks marks) {
        return service.saveMark(marks);
    }

    @PutMapping("/edit/{rollNo}")
    public ResponseEntity<String> update(@Valid @RequestBody Marks marks,@ValidParam(clazz = Marks.class,field = "rollNo") @PathVariable String rollNo) {
        return service.updateMark(rollNo, marks);
    }

    @DeleteMapping("/delete/{rollNo}")
    public ResponseEntity<String> delete(@ValidParam(clazz = Marks.class,field = "rollNo") @PathVariable String rollNo) {
        return service.deleteMark(rollNo);
    }

    @GetMapping("/getAllMarks")
    public ResponseEntity<Iterable<Marks>> getAllMarks() {
        return service.listAll();
    }

    @GetMapping("/find/{rollNo}")
    public ResponseEntity<Marks> findByRollNo(@ValidParam(clazz = Marks.class,field = "rollNo") @PathVariable String rollNo) {
        return service.findByRollNo(rollNo);
    }
}
