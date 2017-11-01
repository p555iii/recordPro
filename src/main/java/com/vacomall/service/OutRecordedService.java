package com.vacomall.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.vacomall.entity.FinOutRecorded;
import com.vacomall.entity.FinRecorded;
import com.vacomall.entity.Page;
import com.vacomall.entity.SysUser;

public interface OutRecordedService extends IService<FinOutRecorded> {

	BigDecimal getThisDayOutRecord(SysUser sysUser);

	BigDecimal getThisMonthOutRecord(SysUser sysUser);

	BigDecimal getThisMonthBalance(SysUser sysUser);

	BigDecimal getThisMonthDayOutRecord(int i, SysUser sysUser);

	List<FinOutRecorded> getThisDayOutRecordList(SysUser sysUser);

	List<FinOutRecorded> getThisMonthOutRecordList(SysUser sysUser);

	double selectHistroyOutRecord(String year, String month, SysUser sysUser);

	BigDecimal getMonthOutRecord(String month, String month2, SysUser sysUser);

	double getBalance(String year, String month, SysUser sysUser);

	List<FinOutRecorded> selectHistroyOutRecordList(String year, String month, SysUser sysUser);

	Page<FinOutRecorded> selectPage(Integer pageNumber, Integer pageSize,
			SysUser sysUser, String search);
	
}
