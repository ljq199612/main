package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.aspectj.lang.annotation.Log;
import com.rz.iot.bpo.framework.aspectj.lang.enums.BusinessType;
import com.rz.iot.bpo.framework.web.controller.BaseController;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.param.bpoPerson.BpoPersonParam;
import com.rz.iot.bpo.model.show.bpoPerson.BpoPersonDetailShow;
import com.rz.iot.bpo.model.show.bpoPerson.BpoPersonShow;
import com.rz.iot.bpo.service.BpoPersonService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 描述 : 人员信息Controller
 * 作者 : Rycony
 * 创建时间 : 2020/6/29 10:04
 * todo 未做数据权限区分
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@RestController
@RequestMapping("/bpoPerson")
public class BpoPersonController extends BaseController {
    @Resource
    private BpoPersonService bpoPersonService;

    /**
     * 查询所有人员所属公司
     * @return
     */
    @RequestMapping("/findPersonCom")
    public Result findPersonCom(){
        return bpoPersonService.findPersonCom();
    }

    /**
     * 查询所有人员信息
     * @param personParam 查询参数
     */
    @RequestMapping("/findAllPerson")
    public Result findAllPerson(BpoPersonParam personParam){
        startPage();
        List<BpoPersonShow> list = bpoPersonService.findAllPerson(personParam);
        return getDataTable(list);
    }

    /**
     * 根据人员id查询人员信息
     * @param personId 人员id
     * @return
     */
    @RequestMapping("/detailPerson")
    public Result detailPerson(Integer personId){
        return bpoPersonService.detailPerson(personId);
    }

    /**
     * 修改人员信息
     * @param bpoPersonDetailShow 人员信息
     * @return
     */
    @RequestMapping("/updatePerson")
    @Log(title = "人员信息",businessType = BusinessType.UPDATE)
    public Result updatePerson(@RequestBody BpoPersonDetailShow bpoPersonDetailShow){
        return bpoPersonService.updatePerson(bpoPersonDetailShow);
    }

    /**
     * 查看身份证
     */
    @RequestMapping("/lookIdCard")
    @Log(title = "查看身份证",businessType = BusinessType.OTHER)
    public Result lookIdCard(Integer personId){
        return bpoPersonService.lookIdCard(personId);
    }

    /**
     * 下载文件
     * @param url 文件url
     * @param response 响应
     * @return
     */
    @RequestMapping("/downLoadFile")
    public Result downLoadContractFile(String url,String fileName, HttpServletResponse response){
        return bpoPersonService.downLoadContractFile(url,fileName,response);
    }

    /**
     * 根据当前用户查询组织架构
     *
     */
    @RequestMapping("/findSysDept")
    public Result findSysDeptShow(){
        return bpoPersonService.findSysDeptShow();
    }

    /**
     * 新增人员信息
     * @param bpoPersonDetailShow 人员信息
     * @return
     */
    @RequestMapping("/addPerson")
    @Log(title = "新增人员信息",businessType = BusinessType.INSERT)
    public Result addPerson(@RequestBody BpoPersonDetailShow bpoPersonDetailShow){
        return bpoPersonService.addPerson(bpoPersonDetailShow);
    }

    /**
     * 查询当前用户所属公司,组织架构
     * @return
     */
    @RequestMapping("/currentUserCom")
    public Result currentUserComOrg(){
        return bpoPersonService.currentUserComOrg();
    }

    /**
     * 导出人员信息
     * @param personParam 人员信息
     * @return
     */
    @RequestMapping("/exportPerson")
    @Log(title = "导出人员信息",businessType = BusinessType.EXPORT)
    public Result exportPerson(BpoPersonParam personParam,HttpServletResponse response){
        return bpoPersonService.exportPerson(personParam,response);
    }

    /**
     * 导出人员模板
     * @return
     */
    @RequestMapping("/exportPersonModel")
    public Result exportPersonModel(HttpServletResponse response){
        return bpoPersonService.exportPersonModel(response);
    }

    /**
     * 导入人员信息,当前文件控制在只能上传一个文件
     * @param files
     * @return
     */
    @RequestMapping("/importPerson")
    @Log(title = "导入人员信息",businessType = BusinessType.IMPORT)
    public Result importPerson(MultipartFile files){
        return bpoPersonService.importPerson(files);
    }
}
