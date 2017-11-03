package com.vacomall.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName("fin_family")
public class FinFamily extends Model<FinFamily>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5178715162062650682L;
	
	@TableId(type=IdType.UUID)
	private String id;
	private String name;
	private String family_host;
	
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

	public String getFamily_host() {
		return family_host;
	}

	public void setFamily_host(String family_host) {
		this.family_host = family_host;
	}

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return null;
	}

}
