package com.data.hkt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DepartmentModel {
    private int departmentId;

    @NotBlank(message = "Tên phòng ban không được để trống")
    @Size(min = 2, max = 100, message = "Tên phòng ban phải từ 2 đến 100 ký tự")
    private String departmentName;

    @Size(max = 500, message = "Mô tả không vượt quá 500 ký tự")
    private String description;

    private Boolean status;

}
