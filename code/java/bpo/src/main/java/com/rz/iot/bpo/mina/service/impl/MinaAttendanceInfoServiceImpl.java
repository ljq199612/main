package com.rz.iot.bpo.mina.service.impl;

import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.mapper.BpoAttendanceAuditRecordMapper;
import com.rz.iot.bpo.mapper.BpoAttendanceInfoMapper;
import com.rz.iot.bpo.mapper.BpoPersonMapper;
import com.rz.iot.bpo.mina.model.show.MinaAttendanceDetailShow;
import com.rz.iot.bpo.mina.model.show.MinaAttendanceInfoShow;
import com.rz.iot.bpo.mina.service.MinaAttendanceInfoService;
import com.rz.iot.bpo.model.BpoAttendanceAuditRecord;
import com.rz.iot.bpo.model.BpoAttendanceInfo;
import com.rz.iot.bpo.model.BpoPerson;
import com.rz.iot.bpo.model.param.BpoAttendanceInfoParam;
import com.rz.iot.bpo.model.show.BpoAttendanceInfoShow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MinaAttendanceInfoServiceImpl implements MinaAttendanceInfoService {

    @Resource
    private BpoAttendanceInfoMapper infoMapper;

    @Resource
    private BpoAttendanceAuditRecordMapper auditRecordMapper;

    @Resource
    private BpoPersonMapper personMapper;

    @Override
    public List<MinaAttendanceInfoShow> findAll(Map<String, Object> params) {
        List<MinaAttendanceInfoShow> list = infoMapper.mpGetAll(params);
        return list;
    }

    @Override
    public MinaAttendanceDetailShow getDetail(Integer id) {
        MinaAttendanceDetailShow detailShow = infoMapper.mpGetDetail(id);
        if (detailShow != null && !detailShow.getRecordApprovalStatus().equals(DictDataConstants.PENDING_REVIEW)) {
            List<BpoAttendanceAuditRecord> auditRecordList = auditRecordMapper.mpAuditDetail(id);
            detailShow.setAuditRecordList(auditRecordList);
        }
        return detailShow;
    }

    @Override
    public List<BpoAttendanceInfoShow> getListByMonth(Date startTime, Date endTime) {
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();
        List<BpoAttendanceInfoShow> list = infoMapper.mpGetListByMonth(userId,startTime,endTime);
        return list;
    }

    @Override
    public void complain(Map<String,Object> params) {
        Integer attendanceId = Integer.parseInt(params.get("id").toString());
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();
        BpoAttendanceInfo attendanceInfo = infoMapper.selectByIdAndUserId(attendanceId,userId);
        //TODO 非异常状态也不能申诉
        if (attendanceInfo == null ||
            !attendanceInfo.getRecordApprovalStatus().equals(DictDataConstants.PENDING_CONFIRM) ||
            attendanceInfo.getRecordApprovalStatus().equals(DictDataConstants.COMPLAIN)
        ) {
            throw new RuntimeException("操作异常！");
        }
        BpoAttendanceInfo info = new BpoAttendanceInfo();
        info.setId(attendanceInfo.getId());
        info.setRecordApprovalStatus(DictDataConstants.COMPLAIN);
        info.setComplainRemark(params.get("complainRemark").toString());
        infoMapper.updateByPrimaryKeySelective(info);
    }

    @Override
    public List<BpoAttendanceInfoShow> abnormalList() {
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();
        List<BpoAttendanceInfoShow> list = infoMapper.abnormalList(userId);
        return list;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public void confirm(BpoAttendanceInfoParam attendanceInfoParam) {

    }
}
