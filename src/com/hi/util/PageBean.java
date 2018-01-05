package com.hi.util;

import java.util.List;
/**
	private List<T> beanList; //查询数据的集合<p>	
	private Integer totalCount;	//共多少条记录<p>
	private Integer totalPage;	//共多少页<p>
	private Integer pageCode = 1;	//第几页<p>
	private Integer pageSize = 2;	//页面显示多少条记录<p>
 * @author 王才
 *
 * @param <T>
 */
public class PageBean<T> {
	//查询数据的集合
	private List<T> beanList;
	//共多少条记录
	private Integer totalCount;
	//共多少页
	private Integer totalPage;
	//第几页
	private Integer pageCode = 1;
	//页面显示多少条记录
	private Integer pageSize = 2;
	
	public PageBean() {
		super();
	}
	public PageBean(List<T> beanList, Integer totalCount,  Integer pageCode, Integer pageSize) {
		super();
		this.beanList = beanList;
		this.totalCount = totalCount;
		this.pageCode = pageCode;
		this.pageSize = pageSize;
		this.totalPage = totalCount%pageSize == 0? totalCount/pageSize : totalCount/pageSize + 1; 
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		if(totalCount <= 0 || pageSize == 0) return 0;
		this.totalPage = totalCount%pageSize == 0? totalCount/pageSize : totalCount/pageSize + 1;
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPageCode() {
		return pageCode;
	}
	public void setPageCode(Integer pageCode) {
		this.pageCode = pageCode;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "PageBean [beanList=" + beanList + ", totalCount=" + totalCount + ", totalPage=" + totalPage
				+ ", pageCode=" + pageCode + ", pageSize=" + pageSize + "]";
	}
	
	
}
