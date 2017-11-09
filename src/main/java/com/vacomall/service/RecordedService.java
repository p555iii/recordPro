package com.vacomall.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.vacomall.entity.FinRecorded;
import com.vacomall.entity.Page;
import com.vacomall.entity.SysUser;

public interface RecordedService extends IService<FinRecorded> {

	BigDecimal getThisMonthRecord(SysUser sysUser);

	List<FinRecorded> getThisMonthRecordList(SysUser sysUser);

	double selectHistroyRecord(String year, String month, SysUser sysUser);

	Page<FinRecorded> selectPage(Integer pageNumber, Integer pageSize,
			SysUser sysUser, String search);

	List<FinRecorded> getRecordList(String year, String month, SysUser sysUser);
	
}
