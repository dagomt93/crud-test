package com.testCrud.crud.controller;


import com.testCrud.crud.dto.Mensaje;
import com.testCrud.crud.dto.ClientDto;
import com.testCrud.crud.entity.Employees;
import com.testCrud.crud.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    private static final String NO_EXIST = "No existe el empleado";

    @GetMapping("/listEmployee")
    public ResponseEntity<List<Employees>> listEmployee(){
        return new ResponseEntity<>(employeeService.listEmployee(), HttpStatus.OK);
    }

    @GetMapping("/detailsEmployee/{idEmployee}")
    public ResponseEntity<Employees> employeeById(@PathVariable("idEmployee") int idEmployee)  {

            if (!employeeService.existsByIdEmployee(idEmployee))
                return new ResponseEntity(new Mensaje(NO_EXIST), HttpStatus.NOT_FOUND);

        Employees employees = employeeService.getEmployee(idEmployee);
        return new ResponseEntity(employees, HttpStatus.OK);
    }

    @GetMapping("/detailsEmployeeByPhone/{phoneEmployee}")
    public ResponseEntity<Employees> employeeByPhone(@PathVariable("phoneEmployee") String phoneEmployee){

            if (!employeeService.existsByPhoneEmployee(phoneEmployee))
                return new ResponseEntity(new Mensaje(NO_EXIST), HttpStatus.NOT_FOUND);

        return new ResponseEntity(employeeService.getByPhoneEmployee(phoneEmployee), HttpStatus.OK);
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<Mensaje> createEmployee(@RequestBody ClientDto clientDto){

        if(StringUtils.isBlank(clientDto.getName()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        if(employeeService.existsByLastName(clientDto.getLastName()))
            return new ResponseEntity(new Mensaje("El apellido lo tiene otro empleado"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(clientDto.getLastName()))
            return new ResponseEntity(new Mensaje("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);

        if(clientDto.getAge()<0 || (Integer) clientDto.getAge() == null)
            return new ResponseEntity(new Mensaje("La edad debe ser mayor a 0"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(clientDto.getPhone()))
            return new ResponseEntity(new Mensaje("El telefono es obligatorio"), HttpStatus.BAD_REQUEST);

        if(employeeService.existsByPhoneEmployee(clientDto.getPhone()))
            return new ResponseEntity(new Mensaje("El telefono lo tiene asignado otro empleado"), HttpStatus.BAD_REQUEST);

        Employees employees = new Employees(clientDto.getName(), clientDto.getLastName(), clientDto.getPhone(), clientDto.getAge());
        employeeService.saveEmployee(employees);
        return new ResponseEntity(new Mensaje("Empleado creado"), HttpStatus.OK);
    }

    @PutMapping("/updateEmployee/{idEmployee}")
    public ResponseEntity<Mensaje> updateEmployee(@PathVariable("idEmployee") int idEmployee, @RequestBody ClientDto clientDto) {

        if (!employeeService.existsByIdEmployee(idEmployee))
            return new ResponseEntity(new Mensaje(NO_EXIST), HttpStatus.NOT_FOUND);

        if (employeeService.existsByPhoneEmployee(clientDto.getPhone())
                && employeeService.getByPhoneEmployee(clientDto.getPhone()).getIdEmployee() != idEmployee)
            return new ResponseEntity(new Mensaje("El empleado ya existe"), HttpStatus.NOT_FOUND);

        if(StringUtils.isBlank(clientDto.getName()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        if(employeeService.existsByLastName(clientDto.getLastName()))
            return new ResponseEntity(new Mensaje("El apellido lo tiene otro empleado"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(clientDto.getLastName()))
            return new ResponseEntity(new Mensaje("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);

        if(clientDto.getAge()<0 || (Integer) clientDto.getAge() == null)
            return new ResponseEntity(new Mensaje("La edad debe ser mayor a 0"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(clientDto.getPhone()))
            return new ResponseEntity(new Mensaje("El telefono es obligatorio"), HttpStatus.BAD_REQUEST);

        if(employeeService.existsByPhoneEmployee(clientDto.getPhone()))
            return new ResponseEntity(new Mensaje("El telefono lo tiene asignado otro empleado"), HttpStatus.BAD_REQUEST);

        Employees employees = employeeService.getEmployee(idEmployee);
        employees.setName(clientDto.getName());
        employees.setLastName(clientDto.getLastName());
        employees.setPhone(clientDto.getPhone());
        employees.setAge(clientDto.getAge());

        employeeService.saveEmployee(employees);
        return new ResponseEntity(new Mensaje("Empleado actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{idEmployee}")
    public ResponseEntity<Mensaje> deleteEmployee(@PathVariable("idEmployee") int idEmployee){
        if (!employeeService.existsByIdEmployee(idEmployee))
            return new ResponseEntity(new Mensaje("No existe el cliente"), HttpStatus.NOT_FOUND);
        employeeService.deleteEmployee(idEmployee);
        return new ResponseEntity(new Mensaje("Empleado eliminado"), HttpStatus.OK);
    }

}
