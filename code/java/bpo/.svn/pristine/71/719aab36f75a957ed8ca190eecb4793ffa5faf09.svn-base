package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.web.controller.BaseController;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.param.bpoPerson.BpoPersonCheckParam;
import com.rz.iot.bpo.model.show.bpoPerson.BpoPersonCheckShow;
import com.rz.iot.bpo.model.show.bpoPerson.BpoPersonDetailShow;
import com.rz.iot.bpo.service.BpoPersonCheckService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 描述 : 人员审核Controller
 * 作者 : Rycony
 * 创建时间 : 2020/7/1 9:45
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@RestController
@RequestMapping("/personCheck")
public class BpoPersonCheckController extends BaseController {
    @Resource
    private BpoPersonCheckService bpoPersonCheckService;

    /**
     * 查询所有未审核人员
     * @param checkParam 查询参数
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll(BpoPersonCheckParam checkParam){
        startPage();
        List<BpoPersonCheckShow> list = bpoPersonCheckService.findAll(checkParam);
        return getDataTable(list);
    }

    /**
     * 获取未审核人员详情
     * @param personId 人员id
     * @return
     */
    @RequestMapping("/personCheckDetail")
    public Result personCheckDetail(Integer personId){
        return bpoPersonCheckService.personCheckDetail(personId);
    }

    /**
     * 审核人员
     *
     * @param personDetailShow 审核人员信息
     */
    @RequestMapping("/checkPerson")
    public Result checkPerson(@RequestBody BpoPersonDetailShow personDetailShow){
        return bpoPersonCheckService.checkPerson(personDetailShow);
    }

    /**
     * 上传多个合同合同文件
     * @param files 合同文件
     * @return
     */
    @RequestMapping("/uploadContractFiles")
    public Result uploadContractFiles(MultipartFile[] files){
        return bpoPersonCheckService.uploadContractFiles(files);
    }

    /**
     * 上传多个身份证附件
     * @param files 身份证附件
     * @return
     */
    @RequestMapping("/uploadPersonFiles")
    public Result uploadPersonFiles(MultipartFile[] files){
        return bpoPersonCheckService.uploadPersonFiles(files);
    }

    /**
     * 删除人员身份附件
     * @param url url
     * @return
     */
    @RequestMapping("/deletePersonFile")
    public Result deletePersonFile(String url){
        return bpoPersonCheckService.deletePersonFile(url);
    }

    /**
     * 删除合同文件
     * @param url 合同url 根据','分割拼接后的字符串
     * @return
     */
    @RequestMapping("/deleteContractFile")
    public Result deleteContractFile(String url){
        return bpoPersonCheckService.deleteContractFile(url);
    }

    /**
     * 同步客户工号
     *
     */
    @RequestMapping("/synClientNo")
    public Result synClientNo(){
        return bpoPersonCheckService.synClientNo();
    }

    /**
     * 查询所有民族
     */
    @RequestMapping("/findAllNation")
    public Result findAllNation(){
        return bpoPersonCheckService.findAllNation();
    }

    /**
     * 查询所有岗位
     * @return
     */
    @RequestMapping("/findAllStation")
    public Result findAllStation(){
        return bpoPersonCheckService.findAllStation();
    }

    /**
     * 查询当前用户下的所有项目
     *
     */
    @RequestMapping("/findProject")
    public Result findCurrentUserProject(){
        return bpoPersonCheckService.findCurrentUserProject();
    }
}
