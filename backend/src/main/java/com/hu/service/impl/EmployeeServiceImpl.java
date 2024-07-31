package com.hu.service.impl;

import com.hu.mapper.EmployeeMapper;
import com.hu.pojo.Employee;
import com.hu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    //獲取全部員工
    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employee = employeeMapper.getAllEmployee();
        return employee;
    }

    //獲取特定員工
    @Override
    public Employee getEmployeeById(String empId) {
        return employeeMapper.getEmployeeById(empId);
    }

    //確認位置是否有人
    @Override
    public boolean checkSeatOccupied(int floorSeatSeq) {
        return employeeMapper.checkSeatOccupied(floorSeatSeq) > 0;
    }

    //更新特定員工位置
    @Override
    @Transactional //確保了方法在執行過程中是原子操作。即如果任何一步操作失敗，整個事務會回滾，確保數據一致性。
    public boolean updateEmployeeSeat(String empId, Integer floorSeatSeq) {
        if(floorSeatSeq!=null){
            //確認位置是否有人
            if (!checkSeatOccupied(floorSeatSeq)) {
                employeeMapper.updateEmployeeSeat(empId, floorSeatSeq);
                return true;
            }
            return false;
        }else{
            //清除
            employeeMapper.updateEmployeeSeat(empId, null);
            return true;
        }
    }


}
