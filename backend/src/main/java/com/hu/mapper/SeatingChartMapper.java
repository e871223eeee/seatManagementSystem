package com.hu.mapper;

import com.hu.pojo.SeatingChart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SeatingChartMapper {
    //獲取全部位置
    @Select("select * from seatingchart")
    List<SeatingChart> getAllSeatingChart();
}
