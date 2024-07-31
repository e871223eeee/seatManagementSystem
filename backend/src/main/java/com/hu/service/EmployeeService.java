package com.hu.service;

import com.hu.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    //獲取全部員工
    List<Employee> getAllEmployee();

    //獲取特定員工
    Employee getEmployeeById(String empId);

    //確認位置是否有人
    boolean checkSeatOccupied(int floorSeatSeq);

    //更新特定員工位置
    boolean updateEmployeeSeat(String empId, Integer floorSeatSeq);
}
