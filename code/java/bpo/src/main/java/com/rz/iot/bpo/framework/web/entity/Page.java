package com.rz.iot.bpo.framework.web.entity;

import java.util.List;

/**
 * 用于分页
 * @param <T>
 */
public class Page<T> {

    //当前页码
    private Integer pageNum;
    //每页显示多少条
    private Integer pageSize;
    //一共多少条数据
    private Long total;
    //一共多少页
    private Integer pages;
    //数据
    private List<T> list;

    public Page() {
        pageNum=1;
        pageSize=10;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", pages=" + pages +
                ", list=" + list +
                '}';
    }
}
