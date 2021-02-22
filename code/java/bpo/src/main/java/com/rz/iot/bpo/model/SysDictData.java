package com.rz.iot.bpo.model;

import lombok.Data;

import java.util.Date;

/**
 * @author rycony
 * 字典数据实体类
 */
@Data
public class SysDictData {
    // 字典编码
    private Long id;

    // 排序
    private Integer dictSort;

    // 标签
    private String dictLabel;

    // 值
    private String dictValue;

    // 类型
    private String dictId;

    // 状态 1.正常 2.禁用 9.删除
    private Byte status;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

    // 备注
    private String remark;
}