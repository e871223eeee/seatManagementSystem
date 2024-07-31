package com.hu.controller;


import com.hu.pojo.Result;
import com.hu.pojo.SeatingChartStateDTO;
import com.hu.pojo.SeatingChart;
import com.hu.service.SeatingChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/SeatingChart")
@CrossOrigin //支持跨域
public class SeatingChartController {
    @Autowired
    SeatingChartService seatingChartService;

    //獲取全部位置
    @GetMapping("/getAllSeatingChart")
    public Result<List<SeatingChart>> getAllSeatingChart(){
        //獲取全部位置
        List<SeatingChart> seatingCharts = seatingChartService.getAllSeatingChart();
        return Result.success(seatingCharts);
    }

    //獲取位置資訊狀態(是否有人占用)
    @GetMapping("/getSeatingChartState")
    public Result<List<SeatingChartStateDTO>> getSeatingChartState(){
        //獲取位置資訊狀態
        List<SeatingChartStateDTO> seatingChartStateDTO = seatingChartService.getSeatingChartState();
        return Result.success(seatingChartStateDTO);
    }
}
