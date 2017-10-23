package com.vacomall.mapper;

import java.util.List;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.vacomall.entity.FinSource;

public interface SourceMapper extends BaseMapper<FinSource> {
	List<FinSource> getSourceByRecord();
	
	List<FinSource> getSourceByNotRecord();
}
