package com.vacomall.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.vacomall.entity.FinRecorded;
import com.vacomall.mapper.RecordedMapper;
import com.vacomall.service.RecordedService;
@Service
public class RecordedServiceImpl extends ServiceImpl<RecordedMapper, FinRecorded> implements RecordedService {
	@Autowired
	private RecordedMapper recordedMapper;
	
	@SuppressWarnings("deprecation")
	@Override
	public BigDecimal getThisMonthRecord() {
		// TODO Auto-generated method stub
		Date date = new Date();
		return recordedMapper.getThisMonthRecord(date.getMonth() + 1);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<FinRecorded> getThisMonthRecordList() {
		Date date = new Date();
		return recordedMapper.getThisMonthRecordList(date.getMonth() + 1);
	}

}
