package com.sjg.sys.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 数据分页类
 * 
 */

public class Pagination<T> implements Serializable {

    private static final long serialVersionUID = -5884976706259160221L;
    /**
     * 上一页
     */
    private Long preIndex;
    /**
     * 当前offset
     */
    private Long curIndex;
    /**
     * 当前页
     */
    private Long curPage;
    /**
     * 下一页
     */
    private Long nextIndex;
    /**
     * 每页条数
     */
    private Long pageSize = 10L;
    /**
     * 总条数
     */
    private Long rowsCount;
    
    /**
     * 总页数
     */
    private Long pagesCount;
    /**
     * ajax请求方法
     */
    private String requestMethod;
    /**
     * 是否有操作
     */
    private Boolean hasOpt;
    /**
     * 操作按钮
     */
    private List<Button> buttons;
    
    

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}

	/**
     * 对象列表
     */
    private List<T> items;
    
    /**
     * 分页插件显示的页数
     */
    private Long pagerCount=5L;
    private Long begin=0L;
    
    private Long end=begin+pagerCount-1;
    

    public void setPreIndex(Long preIndex) {
        this.preIndex = preIndex;
    }

    public void setCurIndex(Long curIndex) {
        this.curIndex = (null == curIndex) ? 1 : curIndex;
    }

    public void setNextIndex(Long nextIndex) {
        this.nextIndex = nextIndex;
    }

    public void setPageSize(Long pageSize) {
    	if (null == pageSize || pageSize < 1L || this.pageSize < 1L) {
    		this.pageSize = 1L;
            this.curIndex = 1L;
        }else{
        	this.pageSize = pageSize;
        }
    	 
    }


    /**
     * 
     * 分页类构建函数
     * 
     */
    public Pagination() {
        updateInfo(1L, 10L, 1L);
    }

    /**
     * 
     * 分页类构建函数
     * 
     * @param pageIndex
     *            当前页码
     * @param pageSize
     *            每页记录数
     */
    public Pagination(Long pageIndex, Long pageSize) {
        updateInfo(pageIndex, pageSize, 0L);
    }

    /**
     * 分页类构建函数
     * 
     * @param pageIndex
     *            当前页码
     * @param pageSize
     *            每页记录数
     * @param rowsCount
     *            记录总数
     */
    public Pagination(Long pageIndex, Long pageSize, Long rowsCount) {
        updateInfo(pageIndex, pageSize, rowsCount);
    }

    /**
     * 获取当前面记录
     * 
     * @return
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * 设置当前页记录
     * 
     * @param items
     */
    public void setItems(List<T> items) {
        this.items = items;
    }

    /**
     * 获取当前页码
     * 
     * @return
     */
    public Long getCurIndex() {
        return curIndex;
    }

    /**
     * 获取下一页码
     * 
     * @return
     */
    public Long getNextIndex() {
        return nextIndex;
    }

    /**
     * 获取总页数
     * 
     * @return
     */
    public Long getPagesCount() {
        return pagesCount;
    }

    /**
     * 获取每页记录数
     * 
     * @return
     */
    public Long getPageSize() {
        return pageSize;
    }

    /**
     * 获取上一页码
     * 
     * @return
     */
    public Long getPreIndex() {
        return preIndex;
    }

    /**
     * 获取总记录数
     * 
     * @return
     */
    public Long getRowsCount() {
        return rowsCount;
    }

    /**
     * 获取首页码
     * 
     * @return
     */
    public Long getFirstIndex() {
        return 1L;
    }

    /**
     * 获取末页码
     * 
     * @return
     */
    public Long getLastIndex() {
        return pagesCount;
    }

    private void updateInfo(Long pageIndex, Long pageSize, Long rowsCount) {
            this.curIndex = pageIndex;
            this.rowsCount = rowsCount;
            this.pageSize = pageSize;
            this.pagesCount=(rowsCount + pageSize - 1L) / pageSize;
    }

    /**
     * 设置总记录数
     * 
     * @param rowsCount
     */
    public void setRowsCount(Long rowsCount) {
        updateInfo(curIndex, pageSize, rowsCount);
    }

    /**
     * 设置总页数
     * 
     * @param pagesCount
     */
    public void setPagesCount(Long pagesCount) {
        this.pagesCount = pagesCount;
    }

	public Long getCurPage() {
		return curPage;
	}

	public void setCurPage() {
		this.curPage = (this.getCurIndex()+1)/this.pageSize+1;
	}

	public Long getBegin() {
		return begin;
	}

	public void setBegin(Long begin) {
		this.begin = begin;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}
	
	public void setBeginandEnd(Pagination<T> p){
		this.setCurPage();
		this.setRequestMethod(p.getRequestMethod());
		if(this.pagesCount<5){
			this.end=this.pagesCount-1;
			if(this.end<0){
				this.end=0L;
			}
			return;
		}
		if(this.begin < 4 && this.curPage.longValue()-1==p.getBegin()&&this.curIndex.longValue()!=0){
			this.begin = 0L;
			this.end = 4L;
			return;
		}
		if(this.curPage.longValue()-1==p.getBegin()&&this.curIndex.longValue()!=0){
			this.begin=p.getBegin()-this.pagerCount+1;
			this.end=p.getEnd()-this.pagerCount+1;
			return;
		}
		if(p.begin<this.curPage&& this.curPage<=p.end){
			
			this.begin=p.getBegin();
			this.end=p.getEnd();
			return;
		}
		if(this.curPage.longValue()==this.pagesCount.longValue()){
			this.begin=this.pagesCount-this.pagerCount;
			this.end=this.pagesCount-1;
			return;
		}
		if((this.curPage.longValue()-1==p.getEnd().longValue())){
			this.begin=p.getBegin()+this.pagerCount-1;
			this.end=p.getEnd()+this.pagerCount-1;
			if(this.end >= this.pagesCount){
				this.begin = this.begin - (this.end - this.pagesCount + 1);
				this.end = this.end - (this.end - this.pagesCount + 1);
			}
			return;
		}
	}

	public Long getPagerCount() {
		return pagerCount;
	}

	public void setPagerCount(Long pagerCount) {
		this.pagerCount = pagerCount;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public Boolean getHasOpt() {
		return hasOpt;
	}

	public void setHasOpt(Boolean hasOpt) {
		this.hasOpt = hasOpt;
	}
	/**
	 * 计算分页循环下标 开始值和结束值(只适用于顾问分页 )
	* @author wenjie
	* @date 2016年10月28日
	* @param Pagination
	* @version 1.0
	 */
	public void setBeginAndEnd(Pagination<T> p){
		this.setCurPage();
		this.setRequestMethod(p.getRequestMethod());
		if(this.pagesCount<5){//总<5 
			this.begin =1L;
			this.end=this.pagesCount;
			return;
		}else{
			if((curPage-1)>=1 && (curPage+3)<=pagesCount){
				this.begin =curPage-1;
				this.end=curPage+3;
				return;
			}
			if((curPage-1)>=1 && (curPage+3)>pagesCount){
				this.begin =pagesCount-4;
				this.end=pagesCount;
				return;
			}
			if((curPage-1)<1 && (curPage+4)<=pagesCount ){
				this.begin =1L;
				this.end=curPage+4;
				return;
			}
		}
	}

}