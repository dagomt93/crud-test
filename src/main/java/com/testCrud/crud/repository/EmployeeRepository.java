package com.testCrud.crud.repository;


import com.testCrud.crud.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {

    Optional<Employees> findByPhone(String nameEmployee);

    boolean existsByPhone(String phoneEmployee);

    boolean existsByLastName(String lastName);
}
