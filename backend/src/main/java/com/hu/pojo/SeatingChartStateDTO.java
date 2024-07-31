package com.hu.pojo;

import lombok.Data;

@Data
public class SeatingChartStateDTO {
    private String empId;
    private int floorSeatSeq;
    private String floorNo;
    private int seatNo;
    private boolean occupied;
}
