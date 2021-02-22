package com.rz.iot.bpo.mina.model.show;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @program: bpo-preview
 * @description: 工作经历展示
 * @author: YangShun
 * @create: 2020-07-22 17:48
 **/
@Data
public class WorkExperienceShow {
    //地点
    private String area;
    //场地
    private String station;
    //工作岗位
    private String jobs;
    //驻场联系人
    private String LinkMan;
    //离职时间
    private Date dimissiontime;
    //入职时间
    private Timestamp createTime;
    //时间展示
    private String timeShow;
}
