package com.vacomall.controller.family;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.vacomall.common.bean.Rest;
import com.vacomall.common.controller.SuperController;
import com.vacomall.common.util.ShiroUtil;
import com.vacomall.entity.FinFamily;
import com.vacomall.entity.FinRecorded;
import com.vacomall.entity.FinSource;
import com.vacomall.entity.Page;
import com.vacomall.entity.SysUser;
import com.vacomall.service.FamilyService;
import com.vacomall.service.ISysUserService;

@Controller
@RequestMapping("/family/")
public class FamilyController extends SuperController{
	@Autowired 
	private ISysUserService sysUserService;
	@Autowired 
	private FamilyService familyService;
    @RequestMapping("/list/{pageNumber}")  
    public  String list(@PathVariable Integer pageNumber,@RequestParam(defaultValue="15") Integer pageSize, String search,Model model){
    	//得到当前登录用户  然后通过用户信息得到所属famliy
    	SysUser sysUser = sysUserService.selectById(ShiroUtil.getSessionUid());
    	model.addAttribute("pageSize",pageSize);
		// 查询分页
		EntityWrapper<SysUser> ew = new EntityWrapper<SysUser>();
		if(StringUtils.isNotBlank(search)){
			ew.like("userName", search);
			model.addAttribute("search",search);
		}
		Page<SysUser> page = familyService.selectPage(pageNumber, pageSize, sysUser,search);
		com.baomidou.mybatisplus.plugins.Page<SysUser> pageData = new com.baomidou.mybatisplus.plugins.Page<SysUser>(page.getPageNow(),page.getPageSize());
		pageData.setRecords(page.getList());
		pageData.setTotal(page.getCount());
		model.addAttribute("pageData", pageData);
		return "family/list";
	}
    
    @RequestMapping("/add")  
    public  String add(){
    	return "family/add";
    }
    
    @RequestMapping("/doAdd")
    @ResponseBody
    public Rest doAdd(SysUser sysUser){
    	//得到当前登录用户  然后通过用户信息得到所属famliy
    	SysUser loginUser = sysUserService.selectById(ShiroUtil.getSessionUid());
    	if(sysUser.getUserName() == null || sysUser.getPassword() == null){
    		return Rest.failure("账户或密码为空");
    	}
    	if(sysUser.getUserName().equals("") || sysUser.getPassword().equals("")){
    		return Rest.failure("账户或密码为空");
    	}
    	sysUser.setCreateTime(new Date());
    	sysUser.setDeptId("c1ca3489da2942909df8aad30fab436e");
    	sysUser.setUserState(1);
    	sysUser.setUserImg("http://news.mydrivers.com/Img/20110518/04481549.png");
    	sysUser.setFamily_id(loginUser.getFamily_id());
    	String[] arr = {"2"};
    	sysUserService.insertUser(sysUser,arr);
    	return Rest.ok();
    }
}
