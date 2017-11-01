package com.vacomall.entity;

import java.util.List;

public class Page<T>  {
	private int pageNow;
	private int pageSize;
	private int count;
	private int totalPage;
	private List<T> list;
	private Object param;
	
	
	
	
	public Object getParam() {
		return param;
	}
	public void setParam(Object param) {
		this.param = param;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Page(int pageNow, int pageSize, int count) {
		this.pageSize = pageSize;
		this.count = count;
		this.totalPage = (int) Math.ceil(this.count*1.0/this.pageSize);
		if(pageNow<=1){  //���������pageNow �ȽϺã���ֹԴ���Ĵ���
			this.pageNow = 1;
		}else if(pageNow>=this.totalPage){
			this.pageNow = this.totalPage;
		}else {
			this.pageNow = pageNow;
		}
		 
	}
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int setTotalPage(){
		return this.totalPage = (int) Math.ceil(this.count*1.0/this.pageSize);
	}
}
