package com.hu.pojo;


import lombok.Data;

@Data
public class Employee {
    private String empId; //員編
    private String name; //員工姓名
    private String email; //員工電子郵件
    private Integer floorSeatSeq; //座位資訊 // 使用 Integer 可以為空 null
}
