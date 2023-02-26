package com.testCrud.crud.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientDto {

    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @Min(0)
    private int age;
    @NotBlank @Min(0) @Max(10)
    private String phone;


}
