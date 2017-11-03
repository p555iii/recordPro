package com.vacomall.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.vacomall.entity.FinFamily;
import com.vacomall.entity.FinOutRecorded;
import com.vacomall.entity.Page;
import com.vacomall.entity.SysUser;
import com.vacomall.mapper.FamilyMapper;
import com.vacomall.service.FamilyService;
@Service
public class FamilyServiceImpl extends ServiceImpl<FamilyMapper,FinFamily> implements FamilyService{
	@Autowired
	private FamilyMapper familyMapper;
	@Override
	public Page<SysUser> selectPage(Integer pageNumber, Integer pageSize,
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
		int count = familyMapper.getCount(map);
		Page<SysUser> page = new Page<SysUser>(pageNumber, pageSize, count);
		map.put("page", page);
		page.setParam(map);
		List<SysUser> pages = familyMapper.getPages(map);
		page.setList(pages);
		return page;
	}

}
