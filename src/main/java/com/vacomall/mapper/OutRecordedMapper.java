package com.vacomall.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.vacomall.entity.FinOutRecorded;

public interface OutRecordedMapper extends BaseMapper<FinOutRecorded>{

	BigDecimal getThisDayOutRecord(Map map);

	BigDecimal getThisMonthOutRecord(Map map);

	BigDecimal getThisMonthBalance(Map map);

	BigDecimal getThisMonthDayOutRecord(Map<String, Object> map);

	List<FinOutRecorded> getThisDayOutRecordList(Map<String, Object> map);

	
	double selectHistroyOutRecord(Map<String, Object> map);

	BigDecimal getMonthOutRecord(Map<String, Object> map);

	double getBalance(Map<String, Object> map);

	List<FinOutRecorded> selectHistroyOutRecordList(Map<String, Object> map);

	List<FinOutRecorded> getThisMonthOutRecordList(Map<String, Object> map);

	int getCount(Map<String, Object> map);

	List<FinOutRecorded> getPages(Map<String, Object> map);

}
