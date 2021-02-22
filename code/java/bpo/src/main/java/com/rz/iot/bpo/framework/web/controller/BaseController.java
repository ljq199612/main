package com.rz.iot.bpo.framework.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.framework.web.page.TableSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * web层通用数据处理
 * 
 * @author ruoyi
 */
public class BaseController<T>
{
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        Page page = TableSupport.buildPageRequest();
        Integer pageNum = page.getPageNum();
        Integer pageSize = page.getPageSize();
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected Result getDataTable(List<?> list) {

        return Result.success(new PageInfo(list), "请求成功！");
    }

    protected Result getData(List<T> list){
        PageInfo<T> pageInfo = new PageInfo<> (list);
        Page<T> page = new Page<> ();
        page.setList (list);
        page.setTotal (pageInfo.getTotal ());
        page.setPages (pageInfo.getPages ());
        return Result.success (page);
    }
    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected Result toAjax(int rows)
    {
        return rows > 0 ? Result.success() : Result.error();
    }
}
