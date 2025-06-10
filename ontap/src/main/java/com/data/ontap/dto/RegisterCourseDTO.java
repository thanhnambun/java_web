package com.data.ontap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCourseDTO {
    private long id;
    private String studentName;
    private String courseName;
}
