package com.rz.iot.bpo.framework.web.page;

import com.rz.iot.bpo.common.utils.ServletUtils;
import com.rz.iot.bpo.framework.web.entity.Page;

/**
 * 表格数据处理
 * 
 * @author ruoyi
 */
public class TableSupport
{
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 封装分页对象
     */
    public static Page getPageDomain()
    {
        Page page = new Page();
        page.setPageNum(ServletUtils.getParameterToInt(PAGE_NUM));
        page.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE));
        return page;
    }

    public static Page buildPageRequest()
    {
        return getPageDomain();
    }
}
