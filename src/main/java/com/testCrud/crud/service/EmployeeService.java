package com.testCrud.crud.service;

import com.testCrud.crud.entity.Employees;
import com.testCrud.crud.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employees> listEmployee(){
        return  employeeRepository.findAll();
    }

    public Employees getEmployee(int idEmployee){
        Employees employees = new Employees();
        Optional<Employees> emp = employeeRepository.findById(idEmployee);
        if (emp.isPresent()) {
            employees = emp.get();
        }
        return  employees;
    }

    public Employees getByPhoneEmployee(String nameEmployee){
        Employees employees = new Employees();
        Optional<Employees> emp = employeeRepository.findByPhone(nameEmployee);
        if (emp.isPresent()) {
            employees = emp.get();
        }
        return employees;
    }

    public void saveEmployee(Employees employees){
        employeeRepository.save(employees);
    }

    public void deleteEmployee(int idEmployee){
        employeeRepository.deleteById(idEmployee);
    }

    public boolean existsByIdEmployee(int idEmployee){
        return employeeRepository.existsById(idEmployee);
    }

    public boolean existsByPhoneEmployee(String phoneEmployee){
        return employeeRepository.existsByPhone(phoneEmployee);
    }

    public boolean existsByLastName(String lastName) {
        return employeeRepository.existsByLastName(lastName);
    }
}
