package com.vacomall.controller;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vacomall.common.util.ShiroUtil;
import com.vacomall.entity.SysUser;
import com.vacomall.service.ISysUserService;
import com.vacomall.service.OutRecordedService;
import com.vacomall.service.RecordedService;
/**
 * 首页控制器
* @ClassName: IndexController
* @author Gaojun.Zhou
* @date 2016年12月8日 下午8:42:40
*  添加统计功能
 */
@Controller
@RequestMapping("/")
public class IndexController {  
	@Autowired
	private OutRecordedService outRecordedService;
	@Autowired
	private RecordedService recordedService;
	@Autowired 
	private ISysUserService sysUserService;
	
    @RequestMapping({"","/","index"})  
    public  String index(Model model){
    	//得到当前登录用户  然后通过用户信息得到所属famliy
    	SysUser sysUser = sysUserService.selectById(ShiroUtil.getSessionUid());
    	//本月入账
    	BigDecimal b =  recordedService.getThisMonthRecord(sysUser);
    	model.addAttribute("sm", b);
    	//今日出账
    	BigDecimal d =  outRecordedService.getThisDayOutRecord(sysUser);
    	model.addAttribute("ct", d);
    	//本月累计出账
    	BigDecimal t =  outRecordedService.getThisMonthOutRecord(sysUser);
    	model.addAttribute("t", t);
    	//本月结余
//    	BigDecimal s =  outRecordedService.getThisMonthBalance(sysUser);
     	//总结余
    	double s = outRecordedService.getBalance(null, null,sysUser);
    	model.addAttribute("s", s);
    	
    	//得到本月天数
    	int day = getCurrentMonthLastDay();
    	StringBuilder sb = new StringBuilder();
    	StringBuilder sb2 = new StringBuilder();
    	for(int i = 1;i <= day; i++){
    		sb.append("\""+i+"\",");
    		BigDecimal money =outRecordedService.getThisMonthDayOutRecord(i,sysUser);
    		if(money.doubleValue() > 0){
    			sb2.append(""+money+",");
    		}else{
    			sb2.append("null,");
    		}
    		
    	}
    	sb.deleteCharAt(sb.length() - 1);
    	sb2.deleteCharAt(sb2.length() - 1);
    	model.addAttribute("data1", sb.toString());
    	model.addAttribute("data2", sb2.toString());
		return "index";
    }  
    
    public int getCurrentMonthLastDay()  
    {  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }  
}
