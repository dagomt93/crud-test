package com.testCrud.crud.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "employees")
@NoArgsConstructor
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmployee;
    private String name;
    private String lastName;
    private int age;
    private String phone;

    public Employees(String name, String lastName, String phone, int age) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
    }
}
