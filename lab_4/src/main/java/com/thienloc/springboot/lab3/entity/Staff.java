package com.thienloc.springboot.lab3.entity;

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
    private String id;
    private String name;
    @Builder.Default
    private Boolean gender = true;
    @Builder.Default
    private String photo = "anhAIdo.jpg";
    @Builder.Default
    private Date birthday = new Date();
    @Builder.Default
    private Double salary = 1000000.0;
    @Builder.Default
    private Integer level = 1;
}
