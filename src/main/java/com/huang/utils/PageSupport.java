package com.huang.utils;

import org.junit.Test;

import javax.lang.model.SourceVersion;

/**
 * @author huanghudong
 * @ClassName PageSupport.java
 * @Description TODO
 * @createTime 2021年05月18日 23:33:00
 */
public class PageSupport {
    //当前页码-来自于用户输入
    private int currentPageNo = 1;

    //总数量（表）
    private int totalCount = 0;

    //页面容量
    private int pageSize = 0;

    //总页数-totalCount/pageSize（+1）
    private int totalPageCount = 1;

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    //OOP三大特性之一：封装（属性私有、get/set，在set中限制一些不安全的情况）优化代码
    public void setCurrentPageNo(int currentPageNo) {
        if (currentPageNo > 0) {
            this.currentPageNo = currentPageNo;
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if (totalCount > 0) {
            this.totalCount = totalCount;
            //设置总记录数
            this.setTotalPageCountByRs();
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public void setTotalPageCountByRs() {
        if (this.totalCount % this.pageSize == 0) {//每页容量>总记录数
            this.totalPageCount = this.totalCount / this.pageSize;//总页数=0
        } else if (this.totalCount % this.pageSize > 0) {//每页容量<总记录数
            this.totalPageCount = this.totalCount / this.pageSize + 1;//总页数多一页
        } else {
            this.totalPageCount = 0;
        }
    }
    @Test
    public void test11(){
        int m=12;
        int n=5;
        System.out.println(m/n);
    }

}
