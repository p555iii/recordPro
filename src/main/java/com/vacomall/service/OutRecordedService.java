package com.vacomall.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.vacomall.entity.FinOutRecorded;

public interface OutRecordedService extends IService<FinOutRecorded> {

	BigDecimal getThisDayOutRecord();

	BigDecimal getThisMonthOutRecord();

	BigDecimal getThisMonthBalance();

	BigDecimal getThisMonthDayOutRecord(int i);

	List<FinOutRecorded> getThisDayOutRecordList();

	List<FinOutRecorded> getThisMonthOutRecordList();

	double selectHistroyOutRecord(String year, String month);

	BigDecimal getMonthOutRecord(String month, String month2);

	double getBalance(String year, String month);

	List<FinOutRecorded> selectHistroyOutRecordList(String year, String month);
	
}
