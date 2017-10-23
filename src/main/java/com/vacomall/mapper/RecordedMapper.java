package com.vacomall.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.vacomall.entity.FinRecorded;

public interface RecordedMapper extends BaseMapper<FinRecorded> {

	BigDecimal getThisMonthRecord(int i);

	List<FinRecorded> getThisMonthRecordList(int i);

}
