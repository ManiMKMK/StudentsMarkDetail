package com.student.StudentsMarkDetail.entity;

public class StudentDTO {
    private String name;
    private String rollNo;
    private String mobile;
    private String profileImg; // Base64 string

    public StudentDTO(String name, String rollNo, String mobile, String profileImg) {
        this.name = name;
        this.rollNo = rollNo;
        this.mobile = mobile;
        this.profileImg = profileImg;
    }

    // Getters and setters
}