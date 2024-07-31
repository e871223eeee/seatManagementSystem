package com.hu.service;

import com.hu.pojo.Employee;
import com.hu.pojo.SeatingChart;
import com.hu.pojo.SeatingChartStateDTO;

import java.util.List;

public interface SeatingChartService {
    //獲取全部位置
    List<SeatingChart> getAllSeatingChart();
    //獲取位置資訊狀態(是否有人占用)
    List<SeatingChartStateDTO> getSeatingChartState();
}
