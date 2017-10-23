package com.vacomall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.vacomall.entity.FinSource;
import com.vacomall.mapper.SourceMapper;
import com.vacomall.service.SourceService;
@Service
public class SourceServiceImpl extends ServiceImpl<SourceMapper, FinSource> implements SourceService {
	@Autowired
	private SourceMapper sourceMapper;
	
	@Override
	public List<FinSource> getSourceByRecord() {
		
		return sourceMapper.getSourceByRecord();
	}

	@Override
	public List<FinSource> getSourceByNotRecord() {
		// TODO Auto-generated method stub
		return sourceMapper.getSourceByNotRecord();
	}
	
}
