package com.vacomall.controller.statistics;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vacomall.common.controller.SuperController;
import com.vacomall.entity.FinOutRecorded;
import com.vacomall.entity.FinSource;
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
	
	@RequestMapping("/st")
	public String st(String year,String month,Model model){
		if(StringUtils.isEmpty(year)){
			year = null;
		}
		if(StringUtils.isEmpty(month)){
			month = null;
		}
		//总收入
		double recordMoney = recordedService.selectHistroyRecord(year,month);
		model.addAttribute("sumR", recordMoney);
		//总支出
		double ct = outRecordService.selectHistroyOutRecord(year,month);
		model.addAttribute("ct", ct);
		//本月累计出账
    	BigDecimal t =  outRecordService.getMonthOutRecord(year,month);
    	model.addAttribute("t", t);
    	//总结余
    	double s1 = outRecordService.getBalance(year, month);
    	model.addAttribute("s", s1);
    	//支出类型
    	List<FinSource> sourceByNotRecord = service.getSourceByNotRecord();
    	StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		//当前年月的支出
		List<FinOutRecorded> list = outRecordService.selectHistroyOutRecordList(year,month);
		for(FinSource s : sourceByNotRecord){
			sb.append("\""+s.getName()+"\",");
			double money = 0;
			for(FinOutRecorded r : list){
				if(s.getId().equals(r.getSourceId())){
					money += r.getMoney();
				}
			}
			System.out.println("d-s");
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
}
