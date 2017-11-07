package com.vacomall.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.vacomall.entity.FinFamily;
import com.vacomall.entity.SysUser;

public interface FamilyMapper extends BaseMapper<FinFamily>{

	int getCount(Map<String, Object> map);

	List<SysUser> getPages(Map<String, Object> map);

	int isHomeMan(SysUser sysUser);

}
