package com.vacomall.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.vacomall.entity.FinOutRecorded;

public interface OutRecordedMapper extends BaseMapper<FinOutRecorded>{

	BigDecimal getThisDayOutRecord(int date);

	BigDecimal getThisMonthOutRecord(int i);

	BigDecimal getThisMonthBalance(int i);

	BigDecimal getThisMonthDayOutRecord(Map<String, Integer> map);

	List<FinOutRecorded> getThisDayOutRecordList(Map<String, Integer> map);

	List<FinOutRecorded> getThisMonthOutRecordList(int month);

}
