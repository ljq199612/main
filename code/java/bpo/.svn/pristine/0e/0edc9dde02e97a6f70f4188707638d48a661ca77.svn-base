package com.rz.iot.bpo.model.show;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 描述 : 用于展示前端显示登录日志和导出登录Excel实体类
 * 作者 : Rycony
 * 创建时间 : 2020/6/27 15:32
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysLoginLogShow extends BaseRowModel {
    @ExcelProperty(value = "所属组织", index = 0)
    private String orgName;

    @ExcelProperty(value = "用户名", index = 1)
    private String userName;

    @ExcelProperty(value = "登录地点", index = 2)
    private String loginLocation;

    @ExcelProperty(value = "操作终端类型", index = 3)
    private String browser;

    @ExcelProperty(value = "登录地址", index = 4)
    private String ipaddr;

    @ExcelProperty(value = "登录日期", index = 5)
    private Date loginTime;

    @ExcelProperty(value = "登录类型", index = 6)
    private String msg;
}
