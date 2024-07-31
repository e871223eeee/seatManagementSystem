package com.hu.controller;

import com.hu.pojo.Employee;
import com.hu.pojo.Result;
import com.hu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employee")
@CrossOrigin //支持跨域
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    //獲取全部員工
    @GetMapping("/getAllEmployee")
    public Result<List<Employee>> getAllEmployee(){
        //獲取全部員工
        List<Employee> employees = employeeService.getAllEmployee();
        return Result.success(employees);
    }

    //獲取特定員工
    @GetMapping("/getEmployeeById")
    public Result<Employee> getEmployeeById(String empId) {
        Employee employee = employeeService.getEmployeeById(empId);
        return Result.success(employee);
    }

    //更新特定員工位置
    @PutMapping("/updateEmployeeSeat")
    public Result updateEmployeeSeat(String empId, Integer floorSeatSeq) {
        //變更位置
        boolean success = employeeService.updateEmployeeSeat(empId, floorSeatSeq);
        if(success){
            return Result.success("Update successful");
        }else{
            return Result.error("Seat already occupied");
        }
    }
}
