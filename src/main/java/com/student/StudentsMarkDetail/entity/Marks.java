package com.student.StudentsMarkDetail.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Marks")
public class Marks {
    @Id
    private ObjectId _id;
    @NotBlank(message = "Roll No is Mandatory")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Allowed Numbers and Alphabets Only")
    private String rollNo;
    @Pattern(regexp = "^[0-9]+$",message = "Numbers Only Allowed")
    @Size(max = 100,min = 0,message = "Invalid marks")
    private int m1;
    @Size(max = 100,min = 0,message = "Invalid marks")
    @Pattern(regexp = "^[0-9]+$",message = "Numbers Only Allowed")
    private int m2;
    @Size(max = 100,min = 0,message = "Invalid marks")
    @Pattern(regexp = "^[0-9]+$",message = "Numbers Only Allowed")
    private int m3;
    Marks(){

    }

    public Marks(String rollNo, int m1, int m2, int m3) {
        this.rollNo = rollNo;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }

    // Getters and Setters
    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public int getM1() {
        return m1;
    }

    public void setM1(int m1) {
        this.m1 = m1;
    }

    public int getM2() {
        return m2;
    }

    public void setM2(int m2) {
        this.m2 = m2;
    }

    public int getM3() {
        return m3;
    }

    public void setM3(int m3) {
        this.m3 = m3;
    }
}
