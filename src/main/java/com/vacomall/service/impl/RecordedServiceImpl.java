package com.vacomall.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.vacomall.entity.FinRecorded;
import com.vacomall.entity.Page;
import com.vacomall.entity.SysUser;
import com.vacomall.mapper.RecordedMapper;
import com.vacomall.service.RecordedService;
@Service
public class RecordedServiceImpl extends ServiceImpl<RecordedMapper, FinRecorded> implements RecordedService {
	@Autowired
	private RecordedMapper recordedMapper;
	
	@SuppressWarnings("deprecation")
	@Override
	public BigDecimal getThisMonthRecord(SysUser sysUser) {
		Date date = new Date();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("month", date.getMonth() + 1);
		map.put("userId", sysUser.getId());
		return recordedMapper.getThisMonthRecord(map);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<FinRecorded> getThisMonthRecordList(SysUser sysUser) {
		Date date = new Date();
		Map map = new HashMap();
		map.put("month", date.getMonth() + 1);
		map.put("userId", sysUser.getId());
		return recordedMapper.getThisMonthRecordList(map);
	}

	@Override
	public double selectHistroyRecord(String year, String month,SysUser sysUser) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("userId", sysUser.getId());
		return recordedMapper.selectHistroyRecord(map);
	}

	@Override
	public Page<FinRecorded> selectPage(Integer pageNumber, Integer pageSize,
			SysUser sysUser, String search) {
		
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
		int count = recordedMapper.getCount(map);
		Page<FinRecorded> page = new Page<FinRecorded>((int)pageNumber, (int)pageSize, count);
		
		map.put("page", page);
		page.setParam(map);
		List<FinRecorded> pages = recordedMapper.getPages(map);
		page.setList(pages);
		return page;
	}

}
