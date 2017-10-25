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
import com.vacomall.entity.FinRecorded;
import com.vacomall.entity.FinSource;
import com.vacomall.entity.SysUser;
import com.vacomall.service.ISysUserService;
import com.vacomall.service.RecordedService;
import com.vacomall.service.SourceService;

/**
 * 入账控制器
 * @author 1
 * 
 */
@Controller
@RequestMapping("/finance/recorded/")
public class RecordedController extends SuperController{
	@Autowired
	private RecordedService recordedService;
	@Autowired
	private SourceService sourceService;
	@Autowired
	private ISysUserService iSysUserService;
	
//	@RequiresPermissions("listRecord")
    @RequestMapping("/list/{pageNumber}")  
    public  String list(@PathVariable Integer pageNumber,@RequestParam(defaultValue="15") Integer pageSize, String search,Model model){
		Page<FinRecorded> page = getPage(pageNumber,pageSize);
		page.setOrderByField("createTime");
		page.setAsc(false);
		model.addAttribute("pageSize",pageSize);
		// 查询分页
		EntityWrapper<FinRecorded> ew = new EntityWrapper<FinRecorded>();
		if(StringUtils.isNotBlank(search)){
			ew.lt("money", search);
			model.addAttribute("search",search);
		}
		Page<FinRecorded> pageData = recordedService.selectPage(page, ew);
		model.addAttribute("pageData", pageData);
		List<FinSource> sourceList = sourceService.getSourceByRecord();
		model.addAttribute("sourceList", sourceList);
		List<SysUser> userList = iSysUserService.getUserList();
		model.addAttribute("userList", userList);
		return "finance/recorded/list";
	}
    
    @RequestMapping("/add")  
    public String add(Model model){
    	List<FinSource> sourceList = sourceService.getSourceByRecord();
		model.addAttribute("sourceList", sourceList);
    	return "finance/recorded/add";
    }
    
	@RequestMapping("/doAdd")
	@ResponseBody
    public Rest doAdd(FinRecorded finRecorded){
    	Date createTime = finRecorded.getCreateTime();
    	Calendar c = Calendar.getInstance();
    	
    	c.setTime(createTime);
    	finRecorded.setYear(createTime.getYear()+1900);
    	finRecorded.setMonth(createTime.getMonth()+1);
    	finRecorded.setDay(createTime.getDate());
    	SysUser sysUser = iSysUserService.selectById(ShiroUtil.getSessionUid());
    	finRecorded.setUserId(sysUser.getId());
    	finRecorded.setCreateTime(new Date());
    	recordedService.insert(finRecorded);
    	return Rest.ok();
    }
	
	@RequestMapping("/delete")
	@ResponseBody
	public Rest delete(String id){
		recordedService.deleteById(id);
		return Rest.ok();
	}
	
	@RequestMapping("/edit")
	public String edit(String id,Model model){
		FinRecorded selectById = recordedService.selectById(id);
		model.addAttribute("record", selectById);
		List<FinSource> sourceList = sourceService.getSourceByRecord();
		model.addAttribute("sourceList", sourceList);
		return "finance/recorded/edit";
	}
	
	@RequestMapping("/doEdit")
	@ResponseBody
	public Rest doEdit(FinRecorded finRecorded){
		FinRecorded selectById = recordedService.selectById(finRecorded.getId());
		Date createTime = finRecorded.getCreateTime();
		selectById.setYear(createTime.getYear()+1900);
		selectById.setMonth(createTime.getMonth()+1);
		selectById.setDay(createTime.getDate());
		selectById.setCreateTime(new Date());
		selectById.setMoney(finRecorded.getMoney());
		selectById.setDescription(finRecorded.getDescription());
		selectById.setSourceId(finRecorded.getSourceId());
		recordedService.updateAllColumnById(selectById);
		return Rest.ok();
	}
}
