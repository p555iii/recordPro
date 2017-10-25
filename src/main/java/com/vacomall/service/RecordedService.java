package com.vacomall.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.vacomall.entity.FinRecorded;

public interface RecordedService extends IService<FinRecorded> {

	BigDecimal getThisMonthRecord();

	List<FinRecorded> getThisMonthRecordList();

	double selectHistroyRecord(String year, String month);
	
}
