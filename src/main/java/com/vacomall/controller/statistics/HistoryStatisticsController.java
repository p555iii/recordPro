package com.vacomall.controller.statistics;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vacomall.common.controller.SuperController;
import com.vacomall.common.util.ShiroUtil;
import com.vacomall.entity.FinOutRecorded;
import com.vacomall.entity.FinRecorded;
import com.vacomall.entity.FinSource;
import com.vacomall.entity.SysUser;
import com.vacomall.service.ISysUserService;
import com.vacomall.service.OutRecordedService;
import com.vacomall.service.RecordedService;
import com.vacomall.service.SourceService;
/**
 * 
 * @author 1
 *
 */
@Controller
@RequestMapping("/statistics/history/")
public class HistoryStatisticsController extends SuperController{
	@Autowired
	private RecordedService recordedService;
	@Autowired
	private OutRecordedService outRecordService;
	@Autowired
	private SourceService service;
	@Autowired 
	private ISysUserService sysUserService;
	
	@RequestMapping("/st")
	public String st(String year,String month,Model model){
		//得到当前登录用户  然后通过用户信息得到所属famliy
    	SysUser sysUser = sysUserService.selectById(ShiroUtil.getSessionUid());
		if(StringUtils.isEmpty(year)){
			year = null;
		}
		if(StringUtils.isEmpty(month)){
			month = null;
		}
		//总收入
		double recordMoney = recordedService.selectHistroyRecord(year,month,sysUser);
		model.addAttribute("sumR", recordMoney);
		//总支出
		double ct = outRecordService.selectHistroyOutRecord(year,month,sysUser);
		model.addAttribute("ct", ct);
		//本月累计出账
    	BigDecimal t =  outRecordService.getMonthOutRecord(year,month,sysUser);
    	model.addAttribute("t", t);
    	//总结余
    	double s1 = outRecordService.getBalance(year, month,sysUser);
    	model.addAttribute("s", s1);
    	//支出类型
    	List<FinSource> sourceByNotRecord = service.getSourceByNotRecord();
    	StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		//当前年月的支出
		List<FinOutRecorded> list = outRecordService.selectHistroyOutRecordList(year,month,sysUser);
		for(FinSource s : sourceByNotRecord){
			sb.append("\""+s.getName()+"\",");
			double money = 0;
			for(FinOutRecorded r : list){
				if(s.getId().equals(r.getSourceId())){
					money += r.getMoney();
				}
			}
			sb2.append("{value:"+money+", name:\""+s.getName()+"\"},");
		}
		sb.deleteCharAt(sb.length() - 1);
    	sb2.deleteCharAt(sb2.length() - 1);
    	model.addAttribute("data1", sb.toString());
    	model.addAttribute("data2", sb2.toString());
    	//统计表总的年
    	List<String> years = service.findAllYear();
    	model.addAttribute("years", years);
    	//统计所有表中的月
    	List<String> months = service.findAllMonth();
    	model.addAttribute("months", months);
    	model.addAttribute("month", month);
    	model.addAttribute("year", year);
		return "/statistics/history/indexHis";
	}
	@RequestMapping("/SumRecord")
	public String SumRecord(String year,String month,Model model){
		//得到当前登录用户  然后通过用户信息得到所属famliy
    	SysUser sysUser = sysUserService.selectById(ShiroUtil.getSessionUid());
    	if(StringUtils.isEmpty(year)){
    		year = null;
    	}
    	if(StringUtils.isEmpty(month)){
    		month = null;
    	}
    	List<FinRecorded> record = recordedService.getRecordList(year,month,sysUser);
    	//收入类型
    	List<FinSource> sourceByRecord = service.getSourceByRecord();
    	StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		for(FinSource s : sourceByRecord){
			sb.append("\""+s.getName()+"\",");
			double money = 0;
			for(FinRecorded r : record){
				if(s.getId().equals(r.getSourceId())){
					money += r.getMoney();
				}
			}
			sb2.append(""+money+",");
		}
		sb.deleteCharAt(sb.length() - 1);
    	sb2.deleteCharAt(sb2.length() - 1);
    	model.addAttribute("data1", sb.toString());
    	model.addAttribute("data2", sb2.toString());
		return "/statistics/history/Record";
		
	}
	
	@RequestMapping("/SumOutRecord")
	public String sumOutRecord(String year,String month,Model model){
		//得到当前登录用户  然后通过用户信息得到所属famliy
    	SysUser sysUser = sysUserService.selectById(ShiroUtil.getSessionUid());
    	if(StringUtils.isEmpty(year)){
    		year = null;
    	}
    	if(StringUtils.isEmpty(month)){
    		month = null;
    	}
    	List<FinOutRecorded> record = outRecordService.getOutRecordList(year,month,sysUser);
    	//收入类型
    	List<FinSource> sourceByRecord = service.getSourceByNotRecord();
    	StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		for(FinSource s : sourceByRecord){
			sb.append("\""+s.getName()+"\",");
			double money = 0;
			for(FinOutRecorded r : record){
				if(s.getId().equals(r.getSourceId())){
					money += r.getMoney();
				}
			}
			sb2.append(""+money+",");
		}
		sb.deleteCharAt(sb.length() - 1);
    	sb2.deleteCharAt(sb2.length() - 1);
    	model.addAttribute("data1", sb.toString());
    	model.addAttribute("data2", sb2.toString());
		return "/statistics/history/Record";
	}
	
	@RequestMapping("/sumOutRecordMonth")
	public String sumOutRecordMonth(String year,String month,Model model){
		//得到当前登录用户  然后通过用户信息得到所属famliy
    	SysUser sysUser = sysUserService.selectById(ShiroUtil.getSessionUid());
    	Date date = new Date();
    	if(StringUtils.isEmpty(year)){
    		year =Integer.toString(date.getYear()+1900);
    	}
    	if(StringUtils.isEmpty(month)){
    		month = Integer.toString(date.getMonth() + 1);
    	}
    	List<FinOutRecorded> record = outRecordService.getOutRecordList(year,month,sysUser);
    	//收入类型
    	List<FinSource> sourceByRecord = service.getSourceByNotRecord();
    	StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		for(FinSource s : sourceByRecord){
			sb.append("\""+s.getName()+"\",");
			double money = 0;
			for(FinOutRecorded r : record){
				if(s.getId().equals(r.getSourceId())){
					money += r.getMoney();
				}
			}
			sb2.append(""+money+",");
		}
		sb.deleteCharAt(sb.length() - 1);
    	sb2.deleteCharAt(sb2.length() - 1);
    	model.addAttribute("data1", sb.toString());
    	model.addAttribute("data2", sb2.toString());
		return "/statistics/history/Record";
	}
}
