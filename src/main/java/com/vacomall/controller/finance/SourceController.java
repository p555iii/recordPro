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
import com.baomidou.mybatisplus.plugins.Page;
import com.vacomall.common.bean.Rest;
import com.vacomall.common.controller.SuperController;
import com.vacomall.common.util.ShiroUtil;
import com.vacomall.entity.FinOutRecorded;
import com.vacomall.entity.FinRecorded;
import com.vacomall.entity.FinSource;
import com.vacomall.entity.SysUser;
import com.vacomall.service.SourceService;
@Controller
@RequestMapping("/finance/source/")
public class SourceController extends SuperController{
	@Autowired
	private SourceService sourceService;
	
	@RequestMapping("/list/{pageNumber}")  
	public  String list(@PathVariable Integer pageNumber,@RequestParam(defaultValue="15") Integer pageSize, String search,Model model){
		Page<FinSource> page = getPage(pageNumber,pageSize);
		page.setOrderByField("createTime");
		page.setAsc(false);
		model.addAttribute("pageSize",pageSize);
		// 查询分页
		EntityWrapper<FinSource> ew = new EntityWrapper<FinSource>();
		if(StringUtils.isNotBlank(search)){
			ew.in("isRecord", search);
			model.addAttribute("search",search);
		}
		Page<FinSource> pageData = sourceService.selectPage(page, ew);
		model.addAttribute("pageData", pageData);
		return "finance/source/list";
	}
	
	 @RequestMapping("/add")  
	 public String add(Model model){
	    List<FinSource> sourceList = sourceService.getSourceByRecord();
		model.addAttribute("sourceList", sourceList);
	   	return "finance/source/add";
	 }
	 
	 @RequestMapping("/doAdd")
	 @ResponseBody
	 public Rest doAdd(FinSource source){
		 source.setCreateTime(new Date());
		 source.setPid("0");
		 sourceService.insert(source);
	     return Rest.ok();
	 }
	 
	 @RequestMapping("/delete")
	 @ResponseBody
	 public Rest delete(String id){
		sourceService.deleteById(id);
		return Rest.ok();
	 }
	 
	 @RequestMapping("/edit")
	 public String edit(String id,Model model){
		FinSource selectById = sourceService.selectById(id);
		model.addAttribute("source", selectById);
		return "finance/source/edit";
	}
	 
	@RequestMapping("/doEdit")
	@ResponseBody
	public Rest doEdit(FinSource source){
		FinSource selectById = sourceService.selectById(source.getId());
		selectById.setIsRecord(source.getIsRecord());
		selectById.setName(source.getName());
		sourceService.updateAllColumnById(selectById);
		return Rest.ok();
	}
}
