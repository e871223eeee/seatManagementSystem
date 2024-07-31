package com.hu.service.impl;


import com.hu.mapper.EmployeeMapper;
import com.hu.mapper.SeatingChartMapper;
import com.hu.pojo.Employee;
import com.hu.pojo.SeatingChart;
import com.hu.pojo.SeatingChartStateDTO;
import com.hu.service.SeatingChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SeatingChartServiceImpl implements SeatingChartService{

    @Autowired
    private SeatingChartMapper SeatingChartMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    //獲取全部位置
    @Override
    public List<SeatingChart> getAllSeatingChart() {
        List<SeatingChart> seatingChart = SeatingChartMapper.getAllSeatingChart();
        return seatingChart;
    }

    //獲取位置資訊狀態(是否有人占用)
    @Override
    @Transactional //確保了方法在執行過程中是原子操作。即如果任何一步操作失敗，整個事務會回滾，確保數據一致性。
    public List<SeatingChartStateDTO> getSeatingChartState() {
        List<SeatingChart> seatingCharts = SeatingChartMapper.getAllSeatingChart();
        List<Employee> employees = employeeMapper.getAllEmployee();

        // Map 以 floorSeatSeq 為鍵，快速查找
        Map<Integer, Employee> occupiedSeats = employees.stream()
                .filter(employee -> employee.getFloorSeatSeq() != null) // 確保 floorSeatSeq 不為 null
                .collect(Collectors.toMap(Employee::getFloorSeatSeq, employee -> employee));

        List<SeatingChartStateDTO> seatingChartStateDTOs = new ArrayList<>();

        for (SeatingChart seatingChart : seatingCharts) {
            SeatingChartStateDTO dto = new SeatingChartStateDTO();
            dto.setFloorSeatSeq(seatingChart.getFloorSeatSeq());
            dto.setFloorNo(seatingChart.getFloorNo());
            dto.setSeatNo(seatingChart.getSeatNo());
            dto.setOccupied(occupiedSeats.containsKey(seatingChart.getFloorSeatSeq()));
            if (dto.isOccupied()) {
                dto.setEmpId(occupiedSeats.get(seatingChart.getFloorSeatSeq()).getEmpId());
            }
            seatingChartStateDTOs.add(dto);
        }

        return seatingChartStateDTOs;
    }
}
