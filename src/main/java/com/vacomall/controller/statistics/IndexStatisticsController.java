package com.vacomall.controller.statistics;

import java.util.List;

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
 * 用于统计用户消费信息，转换成图表的形式
 * @author 1
 *
 */
@Controller
@RequestMapping("/statistics/index/")
public class IndexStatisticsController extends SuperController{
	@Autowired
	private RecordedService recordedService;
	@Autowired
	private OutRecordedService outRecordService;
	@Autowired
	private SourceService service;
	@Autowired 
	private ISysUserService sysUserService;
	/**
	 * 本月入账统计
	 * @param model
	 * @return
	 */
	@RequestMapping("/monthRecord")
	public String recordStatistics(Model model){
		//得到当前登录用户  然后通过用户信息得到所属famliy
    	SysUser sysUser = sysUserService.selectById(ShiroUtil.getSessionUid());
		//收入类型
		List<FinSource> sourceByRecord = service.getSourceByRecord();
		//当月的收入
		List<FinRecorded> record = recordedService.getThisMonthRecordList(sysUser);
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
		return "/statistics/index/monthRecord";
	}
	/**
	 * 今日出账统计
	 * @param model
	 * @return
	 */
	@RequestMapping("/toDayOutrecord")
	public String toDayOutrecordStatistics(Model model){
		//得到当前登录用户  然后通过用户信息得到所属famliy
    	SysUser sysUser = sysUserService.selectById(ShiroUtil.getSessionUid());
		List<FinSource> sourceByNotRecord = service.getSourceByNotRecord();
		//当天的支出
		List<FinOutRecorded> record = outRecordService.getThisDayOutRecordList(sysUser);
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		for(FinSource s : sourceByNotRecord){
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
		return "/statistics/index/toDaymonthOutRecord";
	}
	/**
	 * 本月出账统计
	 * @param model
	 * @return
	 */
	@RequestMapping("/toMothOutrecord")
	public String toMothOutrecordStatistics(Model model){
		//得到当前登录用户  然后通过用户信息得到所属famliy
    	SysUser sysUser = sysUserService.selectById(ShiroUtil.getSessionUid());
		List<FinSource> sourceByNotRecord = service.getSourceByNotRecord();
		//当天的支出
		List<FinOutRecorded> record = outRecordService.getThisMonthOutRecordList(sysUser);
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		for(FinSource s : sourceByNotRecord){
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
		return "/statistics/index/toMonthmonthOutRecord";
	}
}
