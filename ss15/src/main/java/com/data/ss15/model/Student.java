package com.data.ss15.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String studentId;
    private String name;
    private int age;
    private String className;
    private String email;
    private String address;
    private String phone;
}
