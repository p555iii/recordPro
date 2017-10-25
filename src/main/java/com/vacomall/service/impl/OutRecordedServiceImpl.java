package com.vacomall.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.vacomall.entity.FinOutRecorded;
import com.vacomall.mapper.OutRecordedMapper;
import com.vacomall.service.OutRecordedService;
@Service
public class OutRecordedServiceImpl extends ServiceImpl<OutRecordedMapper, FinOutRecorded> implements OutRecordedService {
	@Autowired
	private OutRecordedMapper OutRecordedMapper;
	@SuppressWarnings("deprecation")
	@Override
	public BigDecimal getThisDayOutRecord() {
		Date date = new Date();
		return OutRecordedMapper.getThisDayOutRecord(date.getDate());
	}
	@SuppressWarnings("deprecation")
	@Override
	public BigDecimal getThisMonthOutRecord() {
		Date date = new Date();
		return OutRecordedMapper.getThisMonthOutRecord(date.getMonth() + 1);
	}
	@Override
	public BigDecimal getThisMonthBalance() {
		Date date = new Date();
		return OutRecordedMapper.getThisMonthBalance(date.getMonth() + 1);
	}
	@Override
	public BigDecimal getThisMonthDayOutRecord(int i) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		Date date = new Date();
		map.put("month", date.getMonth() + 1);
		map.put("day", i);
		return OutRecordedMapper.getThisMonthDayOutRecord(map);
	}
	@SuppressWarnings("deprecation")
	@Override
	public List<FinOutRecorded> getThisDayOutRecordList() {
		Date date = new Date();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("month", date.getMonth() + 1);
		map.put("day", date.getDate());
		return OutRecordedMapper.getThisDayOutRecordList(map);
	}
	@SuppressWarnings("deprecation")
	@Override
	public List<FinOutRecorded> getThisMonthOutRecordList() {
		Date date = new Date();
		return OutRecordedMapper.getThisMonthOutRecordList(date.getMonth() + 1);
	}
	@Override
	public double selectHistroyOutRecord(String year, String month) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("year", year);
		map.put("month", month);
		return OutRecordedMapper.selectHistroyOutRecord(map);
	}
	@Override
	public BigDecimal getMonthOutRecord(String year,String month) {
		if(month == null){
			Date date = new Date();
			month = Double.toString(date.getMonth() + 1);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("year", year);
		map.put("month", month);
		return OutRecordedMapper.getMonthOutRecord(map);
	}
	@Override
	public double getBalance(String year, String month) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("year", year);
		map.put("month", month);
		return OutRecordedMapper.getBalance(map);
	}
	@Override
	public List<FinOutRecorded> selectHistroyOutRecordList(String year,
			String month) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("year", year);
		map.put("month", month);
		return OutRecordedMapper.selectHistroyOutRecordList(map);
	}

}
