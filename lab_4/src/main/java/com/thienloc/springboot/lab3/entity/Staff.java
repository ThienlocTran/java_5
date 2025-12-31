package com.thienloc.springboot.lab3.entity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {
    @NotBlank(message = "chưa nhập email")
    @Email(message = "Email chưa đúng định dạng")
    private String id;

    @NotBlank(message = "Chưa nhập họ và tên")
    private String name;

    @Builder.Default
    @NotNull(message = "Chưa chọn giới tính")
    private Boolean gender = true;
    @Builder.Default
    private String photo = "anhAIdo.jpg";
    @Builder.Default
    @NotNull(message = "Chưa nhập ngày sinh")
    @Past(message = "Ngày sinh không hợp lệ")
    private Date birthday = new Date();
    @Builder.Default
    @Min(value = 1000,message = "Lương tối thiếu phải 1000")
    @NotNull(message = "Chưa nhập lương")
    private Double salary = 1000000.0;
    @Builder.Default
    private Integer level = 1;
}
