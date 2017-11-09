package com.vacomall.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.vacomall.entity.FinRecorded;
import com.vacomall.entity.Page;
import com.vacomall.entity.SysUser;

public interface RecordedMapper extends BaseMapper<FinRecorded> {

	BigDecimal getThisMonthRecord(Map map);

	List<FinRecorded> getThisMonthRecordList(Map map);

	double selectHistroyRecord(Map<String, Object> map);

	int getCount(Map<String, Object> map);

	List<FinRecorded> getPages(Map<String, Object> map);

	List<FinRecorded> getRecordList(Map<String, Object> map);

}
