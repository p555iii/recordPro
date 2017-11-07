package com.vacomall.controller.finance;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.vacomall.entity.FinOutRecorded;
import com.vacomall.entity.FinRecorded;
import com.vacomall.entity.FinSource;
import com.vacomall.entity.Page;
import com.vacomall.entity.SysUser;
import com.vacomall.service.ISysUserService;
import com.vacomall.service.OutRecordedService;
import com.vacomall.service.SourceService;
/**
 * 
 * @author 1
 *
 */
@Controller
@RequestMapping("/finance/outRecorded/")
public class OutRecordedController extends SuperController{
	@Autowired
	private OutRecordedService outRecordedService;
	@Autowired
	private SourceService sourceService;
	@Autowired
	private ISysUserService iSysUserService;
	@Autowired 
	private ISysUserService sysUserService;
	
	@RequestMapping("/list/{pageNumber}")  
	public  String list(@PathVariable Integer pageNumber,@RequestParam(defaultValue="15") Integer pageSize, String search,Model model){
		//得到当前登录用户  然后通过用户信息得到所属famliy
    	SysUser sysUser = sysUserService.selectById(ShiroUtil.getSessionUid());
		model.addAttribute("pageSize",pageSize);
		// 查询分页
		EntityWrapper<FinOutRecorded> ew = new EntityWrapper<FinOutRecorded>();
		if(StringUtils.isNotBlank(search)){
			ew.lt("money", search);
			model.addAttribute("search",search);
		}
		Page<FinOutRecorded> page = outRecordedService.selectPage(pageNumber, pageSize, sysUser,search);
		com.baomidou.mybatisplus.plugins.Page<FinOutRecorded> pageData = new com.baomidou.mybatisplus.plugins.Page<FinOutRecorded>(page.getPageNow(),page.getPageSize());
		pageData.setRecords(page.getList());
		pageData.setTotal(page.getCount());
		model.addAttribute("pageData", pageData);
		List<FinSource> sourceList = sourceService.getSourceByNotRecord();
		model.addAttribute("sourceList", sourceList);
		List<SysUser> userList = iSysUserService.getUserList();
		model.addAttribute("userList", userList);
		return "finance/outRecord/list";
	}
	
	@RequestMapping("/add")  
    public String add(Model model){
    	List<FinSource> sourceList = sourceService.getSourceByNotRecord();
		model.addAttribute("sourceList", sourceList);
    	return "finance/outRecord/add";
    }
    
	@RequestMapping("/doAdd")
	@ResponseBody
    public Rest doAdd(FinOutRecorded finRecorded){
    	Date createTime = finRecorded.getCreateTime();
    	Calendar c = Calendar.getInstance();
    	if(createTime==null){
    		createTime = new Date();
    	}
    	c.setTime(createTime);
    	finRecorded.setYear(createTime.getYear()+1900);
    	finRecorded.setMonth(createTime.getMonth()+1);
    	finRecorded.setDay(createTime.getDate());
    	SysUser sysUser = iSysUserService.selectById(ShiroUtil.getSessionUid());
    	finRecorded.setUserId(sysUser.getId());
    	finRecorded.setCreateTime(new Date());
    	outRecordedService.insert(finRecorded);
    	return Rest.ok();
    }
	
	@RequestMapping("/delete")
	@ResponseBody
	public Rest delete(String id){
		outRecordedService.deleteById(id);
		return Rest.ok();
	}
	
	@RequestMapping("/edit")
	public String edit(String id,Model model){
		FinOutRecorded selectById = outRecordedService.selectById(id);
		model.addAttribute("record", selectById);
		List<FinSource> sourceList = sourceService.getSourceByNotRecord();
		model.addAttribute("sourceList", sourceList);
		return "finance/outRecord/edit";
	}
	
	@RequestMapping("/doEdit")
	@ResponseBody
	public Rest doEdit(FinOutRecorded finRecorded){
		FinOutRecorded selectById = outRecordedService.selectById(finRecorded.getId());
		Date createTime = finRecorded.getCreateTime();
		selectById.setYear(createTime.getYear()+1900);
		selectById.setMonth(createTime.getMonth()+1);
		selectById.setDay(createTime.getDate());
		selectById.setCreateTime(new Date());
		selectById.setMoney(finRecorded.getMoney());
		selectById.setDescription(finRecorded.getDescription());
		selectById.setSourceId(finRecorded.getSourceId());
		outRecordedService.updateAllColumnById(selectById);
		return Rest.ok();
	}
}
