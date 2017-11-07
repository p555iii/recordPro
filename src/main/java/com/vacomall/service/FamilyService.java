package com.vacomall.service;

import com.baomidou.mybatisplus.service.IService;
import com.vacomall.entity.FinFamily;
import com.vacomall.entity.Page;
import com.vacomall.entity.SysUser;

public interface FamilyService extends IService<FinFamily>{

	Page<SysUser> selectPage(Integer pageNumber, Integer pageSize,
			SysUser sysUser, String search);

	int isHomeMan(SysUser sysUser);

}
