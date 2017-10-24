package com.vacomall.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName("fin_source")
public class FinSource extends Model<FinSource>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@TableId(type=IdType.UUID)
	private String id;
	private String name;
	private String pid;
	private int isRecord;
	private Date createTime;
	
	
	
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getIsRecord() {
		return isRecord;
	}
	public void setIsRecord(int isRecord) {
		this.isRecord = isRecord;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
