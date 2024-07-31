package com.hu.mapper;

import com.hu.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
//防止 SQL 注入  使用#{XXX} 而不是${XXX}

@Mapper
public interface EmployeeMapper {
    //獲取全部員工
    @Select("select * from employee")
    List<Employee> getAllEmployee();

    //獲取特定員工
    @Select("SELECT * FROM Employee WHERE EMP_ID = #{empId}")
    Employee getEmployeeById(String empId);

    //確認位置是否有人
    @Select("SELECT COUNT(*) FROM Employee WHERE FLOOR_SEAT_SEQ = #{floorSeatSeq}")
    int checkSeatOccupied(int floorSeatSeq);

    //更新特定員工位置
    @Update("UPDATE Employee SET FLOOR_SEAT_SEQ = #{floorSeatSeq} WHERE EMP_ID = #{empId}")
    void updateEmployeeSeat(String empId, Integer floorSeatSeq);
}
