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
import com.vacomall.entity.FinRecorded;
import com.vacomall.entity.Page;
import com.vacomall.entity.SysUser;
import com.vacomall.mapper.OutRecordedMapper;
import com.vacomall.service.OutRecordedService;
@Service
public class OutRecordedServiceImpl extends ServiceImpl<OutRecordedMapper, FinOutRecorded> implements OutRecordedService {
	@Autowired
	private OutRecordedMapper OutRecordedMapper;
	@SuppressWarnings("deprecation")
	@Override
	public BigDecimal getThisDayOutRecord(SysUser sysUser) {
		Date date = new Date();
		Map map = new HashMap();
		map.put("day", date.getDate());
		map.put("userId", sysUser.getId());
		return OutRecordedMapper.getThisDayOutRecord(map);
	}
	@SuppressWarnings("deprecation")
	@Override
	public BigDecimal getThisMonthOutRecord(SysUser sysUser) {
		Date date = new Date();
		Map map = new HashMap();
		map.put("month", date.getMonth() + 1);
		map.put("userId", sysUser.getId());
		return OutRecordedMapper.getThisMonthOutRecord(map);
	}
	@Override
	public BigDecimal getThisMonthBalance(SysUser sysUser) {
		Date date = new Date();
		Map map = new HashMap();
		map.put("month", date.getMonth() + 1);
		map.put("userId", sysUser.getId());
		return OutRecordedMapper.getThisMonthBalance(map);
	}
	@Override
	public BigDecimal getThisMonthDayOutRecord(int i,SysUser sysUser) {
		Map<String,Object> map = new HashMap<String,Object>();
		Date date = new Date();
		map.put("day", i);
		map.put("userId", sysUser.getId());
		return OutRecordedMapper.getThisMonthDayOutRecord(map);
	}
	@SuppressWarnings("deprecation")
	@Override
	public List<FinOutRecorded> getThisDayOutRecordList(SysUser sysUser) {
		Map<String,Object> map = new HashMap<String,Object>();
		Date date = new Date();
		map.put("day", date.getDate());
		map.put("userId", sysUser.getId());
		return OutRecordedMapper.getThisDayOutRecordList(map);
	}
	@SuppressWarnings("deprecation")
	@Override
	public List<FinOutRecorded> getThisMonthOutRecordList(SysUser sysUser) {
		Map<String,Object> map = new HashMap<String,Object>();
		Date date = new Date();
		map.put("month", date.getMonth() + 1);
		map.put("userId", sysUser.getId());
		return OutRecordedMapper.getThisMonthOutRecordList(map);
	}
	@Override
	public double selectHistroyOutRecord(String year, String month,SysUser sysUser) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("userId", sysUser.getId());
		return OutRecordedMapper.selectHistroyOutRecord(map);
	}
	@Override
	public BigDecimal getMonthOutRecord(String year,String month, SysUser sysUser) {
		Date date = new Date();
		if(month == null || year == ""){
			
			month = Double.toString(date.getMonth() + 1);
		}
		if(year == null || year == ""){
			year = Double.toString(date.getYear()+1900);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("userId", sysUser.getId());
		return OutRecordedMapper.getMonthOutRecord(map);
	}
	@Override
	public double getBalance(String year, String month,SysUser sysUser) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("userId", sysUser.getId());
		return OutRecordedMapper.getBalance(map);
	}
	@Override
	public List<FinOutRecorded> selectHistroyOutRecordList(String year,
			String month, SysUser sysUser) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("userId", sysUser.getId());
		return OutRecordedMapper.selectHistroyOutRecordList(map);
	}
	@Override
	public Page<FinOutRecorded> selectPage(Integer pageNumber,
			Integer pageSize, SysUser sysUser, String search,String source) {
		if(pageNumber==null){
			pageNumber=1;
		}
		if(pageSize==null){
			pageSize=15;
		}
		if(search!=null && search.equals("")){
			search=null;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", sysUser.getId());
		map.put("search", search);
		map.put("source", source);
		int count = OutRecordedMapper.getCount(map);
		Page<FinOutRecorded> page = new Page<FinOutRecorded>(pageNumber, pageSize, count);
		map.put("page", page);
		page.setParam(map);
		List<FinOutRecorded> pages = OutRecordedMapper.getPages(map);
		page.setList(pages);
		return page;
	}
	@Override
	public List<FinOutRecorded> getOutRecordList(String year, String month,
			SysUser sysUser) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("userId", sysUser.getId());
		return OutRecordedMapper.selectHistroyOutRecordList(map);
	}

}
