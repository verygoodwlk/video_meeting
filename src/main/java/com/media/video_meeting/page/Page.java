package com.media.video_meeting.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页的对象
 * @author ken
 *
 */
public class Page {
	private Integer page = 1;//当前第几页
	private Integer pageSize;//每页显示多少条
	private Integer pageSum;//共有多少页
	private Integer pageCount;//共有多少条
	private List<Integer> indexs;//导航的数字
	private String url;

	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageSum() {
		return pageSum;
	}
	public void setPageSum(Integer pageSum) {
		this.pageSum = pageSum;
		index();
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
		//设置总页码
		this.setPageSum(pageCount % pageSize == 0 ? pageCount / pageSize : pageCount / pageSize + 1);
	}
	public List<Integer> getIndexs() {
		return indexs;
	}
	public void setIndexs(List<Integer> indexs) {
		this.indexs = indexs;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Page [page=" + page + ", pageSize=" + pageSize + ", pageSum=" + pageSum + ", pageCount=" + pageCount
				+ ", indexs=" + indexs + "]";
	}

	/**
	 * 计算当前的分页导航需要显示的页码
	 *
	 * 3 4 5
	 *
	 */
	private void index(){
		Integer page = getPage();
		Integer pageSum = getPageSum();
		if(page == null || pageSum == null){
			return;
		}

		Integer begin = Math.max(1, page - 2);
		Integer end = Math.min(page + 2, pageSum);

		List<Integer> indexs = new ArrayList<Integer>();
		for(int i = begin; i <= end; i++){
			indexs.add(i);
		}
		setIndexs(indexs);
	}
}
