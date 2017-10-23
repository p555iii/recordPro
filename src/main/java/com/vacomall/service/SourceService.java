package com.vacomall.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.vacomall.entity.FinSource;

public interface SourceService extends IService<FinSource>{
	List<FinSource> getSourceByRecord();
	
	List<FinSource> getSourceByNotRecord();
}
