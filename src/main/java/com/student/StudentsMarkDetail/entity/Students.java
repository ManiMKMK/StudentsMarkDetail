package com.student.StudentsMarkDetail.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

@Document(collection = "Students")
public class Students {
    @Id
    private ObjectId _id;
    @NotBlank(message = "Name is Mandatory")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Allowed Alphabets Only")
    private String name;
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Allowed Numbers and Alphabets Only")
    @Indexed(unique = true)
    @NotBlank(message = "RollNo is Mandatory")
    private String rollNo;
    @Size(min = 10,max = 10, message = "Enter 10 Digit mobile Number")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Mobile Number")
    private String mobile;
    private byte[] profileImg;

    public byte[] getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(byte[] profileImg) {
        this.profileImg = profileImg;
    }

    public Students() {
    }

    public Students(String name, String rollNo, String mobile) {
        this.name = name;
        this.rollNo = rollNo;
        this.mobile = mobile;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
