package com.rz.iot.bpo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.utils.ChineseCharacterUtil;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.framework.redis.RedisCache;
import com.rz.iot.bpo.framework.security.LoginUser;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.*;
import com.rz.iot.bpo.model.*;
import com.rz.iot.bpo.model.param.*;
import com.rz.iot.bpo.model.show.*;
import com.rz.iot.bpo.service.ProjectService;
import com.rz.iot.bpo.service.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static com.rz.iot.bpo.common.constant.DictDataConstants.*;
import static com.rz.iot.bpo.common.constant.ResultConstants.REQUEST_SUCCESS;
import static com.rz.iot.bpo.common.constant.ResultConstants.SYS_TYPE_SAME;

@Slf4j
@Service("ProjectService")
public class ProjectServiceImpl implements ProjectService {


    @Resource
    RedisCache redisCache;

    @Resource
    BpoProjectMapper projectMapper;

    @Resource
    BpoWorkModelMapper workModelMapper;

    @Resource
    BpoProcessMapper processMapper;

    @Resource
    BpoWorkGroupMapper workGroupMapper;

    @Resource
    SysWorkModuleRelMapper sysWorkModuleRelMapper;

    @Resource
    BpoFeeRuleByTimeMapper bpoFeeRuleByTimeMapper;

    @Resource
    BpoProductPriceMapper bpoProductPriceMapper;

    @Resource
    BpoFeeRuleMapper bpoFeeRuleMapper;

    @Resource
    SysCompanyMapper sysCompanyMapper;

    @Resource
    BpoContractInfoMapper bpoContractInfoMapper;

    @Resource
    BpoProductMapper bpoProductMapper;

    @Resource
    BpoSupplierInfoMapper bpoSupplierInfoMapper;

    @Resource
    SysAreaRelMapper sysAreaRelMapper;

    @Resource
    BpoTransferStationMapper bpoTransferStationMapper;

    @Resource
    private SysDeptService sysDeptService;


    @Override
    public Result<BpoProjectDetailShow> detail(int id) {
        BpoProjectDetailShow projectDetail = getBpoProjectDetailShow(id);

        Result<BpoProjectDetailShow> result = new Result<>(REQUEST_SUCCESS);
        result.setData(projectDetail);
        return result;
    }

    private BpoProjectDetailShow getBpoProjectDetailShow(int id) {
        BpoProjectDetailShow projectDetail = projectMapper.findProjectDetail(id);

        Integer cityId = projectDetail.getCityId();
        SysAreaRel sysAreaRel = sysAreaRelMapper.selectByPrimaryKey(cityId);
        projectDetail.setSysAreaRel(sysAreaRel);
        return projectDetail;
    }

    /**
     * 添加数据到系统设置工序工作模块工作小组
     * 然后
     * 添加数据到缓存
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result<ProjectConfigParam> addProjectConfig(ProjectConfigParam projectConfigParam) {
        Integer transferStationId = projectConfigParam.getTransferStationId();
        if (transferStationId == null) {
            return Result.requestParamError("transferStationId is null");
        }
        Result<ProjectConfigParam> result = handleProjectConfigParam(projectConfigParam);
        if (result != null) {
            return result;
        }
        String token = getToken();
        redisCache.setCacheObject(PROJECT_CONFIG + token, projectConfigParam, 1, TimeUnit.DAYS);

        Result<ProjectConfigParam> configParamResult = new Result<>(REQUEST_SUCCESS);
        configParamResult.setData(projectConfigParam);
        return configParamResult;
    }

    private String getToken() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        return loginUser.getToken();
    }

    /**
     * 添加工作模块工作小组信息到数据库
     */
    private Result<ProjectConfigParam> handleProjectConfigParam(ProjectConfigParam projectConfigParam) {
        List<BpoWorkModelParam> workModels = projectConfigParam.getWorkModels();
        //如果传入数据id为空则认为需要添加到数据库,然后判断数据是否重复
        boolean isError = false;
        String pattern = "^[0-9a-zA-Z_]+$";
        for (BpoWorkModelParam bpoWorkModelParam : workModels) {
            String sysModuleCode = bpoWorkModelParam.getSysModuleCode();
            String sysModuleName = bpoWorkModelParam.getSysModuleName();
            Integer sysModuleId = bpoWorkModelParam.getSysModuleId();
            boolean isMatch = Pattern.matches(pattern, sysModuleCode);
            if (!isMatch && sysModuleId == null) {
                bpoWorkModelParam.setCodeInvalid(true);
                isError = true;
            }
            if (isNameExist(sysModuleName) && sysModuleId == null) {
                bpoWorkModelParam.setNameExist(true);
                isError = true;
            }
            if (isCodeExist(sysModuleCode) && sysModuleId == null) {
                bpoWorkModelParam.setCodeExist(true);
                isError = true;
            }
            List<BpoGroupParam> bpoGroupParamList = bpoWorkModelParam.getBpoGroupParamList();
            for (BpoGroupParam groupParam : bpoGroupParamList) {
                String sysGroupName = groupParam.getSysGroupName();
                String sysGroupCode = groupParam.getSysGroupCode();
                Integer sysGroupId = groupParam.getSysGroupId();
                if (isNameExist(sysGroupName) && sysGroupId == null) {
                    groupParam.setNameExist(true);
                    isError = true;
                }
                if (isCodeExist(sysGroupCode) && sysGroupId == null) {
                    groupParam.setCodeExist(true);
                    isError = true;
                }
                isMatch = Pattern.matches(pattern, sysGroupCode);
                if (!isMatch && sysGroupId == null) {
                    bpoWorkModelParam.setCodeInvalid(true);
                    isError = true;
                }
                List<BpoProcessParam> processes = groupParam.getProcesses();
                for (BpoProcessParam process : processes) {
                    String sysProcessCode = process.getSysProcessCode();
                    String sysProcessName = process.getSysProcessName();
                    Integer sysProcessId = process.getSysProcessId();
                    if (isNameExist(sysProcessName) && sysProcessId == null) {
                        process.setNameExist(true);
                        isError = true;
                    }
                    isMatch = Pattern.matches(pattern, sysModuleCode);
                    if (!isMatch && sysProcessId == null) {
                        bpoWorkModelParam.setCodeInvalid(true);
                        isError = true;
                    }
                    if (isCodeExist(sysProcessCode) && sysProcessId == null) {
                        process.setCodeExist(true);
                        isError = true;
                    }
                }
            }
        }
        if (isError) {
            Result<ProjectConfigParam> configParamResult = new Result<>(SYS_TYPE_SAME);
            configParamResult.setData(projectConfigParam);
            return configParamResult;
        }
        //
        for (BpoWorkModelParam bpoWorkModelParam : workModels) {

            //查询系统设置表模块信息
            Integer sysWorkModuleRelId = bpoWorkModelParam.getSysModuleId();
            String sysModuleCode = bpoWorkModelParam.getSysModuleCode();
            String sysModuleName = bpoWorkModelParam.getSysModuleName();

            SysWorkModuleRel moduleRel = getSysWorkModuleRel(0, sysWorkModuleRelId, sysModuleName, sysModuleCode, SYS_MODULE);
            sysWorkModuleRelId = moduleRel.getId();

            bpoWorkModelParam.setSysModuleId(sysWorkModuleRelId);

            List<BpoGroupParam> groupSysWorkModuleRelIds = bpoWorkModelParam.getBpoGroupParamList();
            for (BpoGroupParam groupParam : groupSysWorkModuleRelIds) {
                //查询系统设置表  工作小组信息
                Integer sysGroupId = groupParam.getSysGroupId();
                String sysGroupName = groupParam.getSysGroupName();
                String sysGroupCode = groupParam.getSysGroupCode();

                SysWorkModuleRel groupSysWorkModuleRel = getSysWorkModuleRel(sysWorkModuleRelId, sysGroupId, sysGroupName, sysGroupCode, SYS_GROUP);
                sysGroupId = groupSysWorkModuleRel.getId();
                groupParam.setSysGroupId(sysGroupId);

                List<BpoProcessParam> processes = groupParam.getProcesses();
                for (BpoProcessParam process : processes) {
                    //查询系统设置表  工序信息
                    Integer processSysWorkModuleRelId = process.getSysProcessId();
                    String sysProcessCode = process.getSysProcessCode();
                    String sysProcessName = process.getSysProcessName();

                    SysWorkModuleRel processSysWorkModuleRel
                            = getSysWorkModuleRel(sysGroupId, processSysWorkModuleRelId, sysProcessName, sysProcessCode, SYS_PROCESS);

                    process.setSysProcessId(processSysWorkModuleRel.getId());
                }
            }
        }
        return null;
    }

    /**
     * //id,name,code 查询数据,如果查不到添加一条
     *
     * @param type com.rz.iot.bpo.common.constant.DictDataConstants#SYS_MODULE,SYS_GROUP,SYS_PROCESS
     * @return 返回查询到的或新建的SysWorkModuleRel
     */
    private SysWorkModuleRel getSysWorkModuleRel(Integer parentId, Integer id, String name, String code, byte type) {
        SysWorkModuleRel moduleRel = getSysWorkModuleRel(id, code, name);
        if (moduleRel == null) {
            moduleRel = new SysWorkModuleRel();
            moduleRel.setName(name);
            moduleRel.setCode(code);
            moduleRel.setParentId(parentId);
            moduleRel.setType(type);
            sysWorkModuleRelMapper.insertSelective(moduleRel);
        }
        return moduleRel;
    }

    private boolean isNameExist(String sysModuleName) {
        boolean isNameExists = true;
        SysWorkModuleRel existSameCode = sysWorkModuleRelMapper.isExistSameName(sysModuleName);
        if (existSameCode == null) {
            isNameExists = false;
        }
        return isNameExists;
    }

    private boolean isCodeExist(String sysModuleCode) {
        boolean isCodeExists = true;
        SysWorkModuleRel existSameCode = sysWorkModuleRelMapper.isExistSameCode(sysModuleCode);
        if (existSameCode == null) {
            isCodeExists = false;
        }
        return isCodeExists;
    }

    private SysWorkModuleRel getSysWorkModuleRel(Integer sysWorkModuleRelId, String code, String name) {
        SysWorkModuleRel sysWorkModuleRel = sysWorkModuleRelMapper.selectByPrimaryKey(sysWorkModuleRelId);
        if (sysWorkModuleRel == null) {
            sysWorkModuleRel = sysWorkModuleRelMapper.findByNameAndCode(name, code);
        }
        return sysWorkModuleRel;
    }

    private SysWorkModuleRel getSysWorkModuleRel(Integer sysWorkModuleRelId) {
        return getSysWorkModuleRel(sysWorkModuleRelId, null, null);
    }

    /**
     * 校验数据
     * 添加项目基础信息到缓存
     */
    @Override
    public Result addProjectBasicInformation(ProjectBasicInformationParam basicInformationParam) {
        String projectName = basicInformationParam.getProjectName();
        if (StringUtils.isEmpty(projectName)) {
            return Result.requestParamError("projectName is null");
        }
        String projectSn = basicInformationParam.getProjectSn();
        if (StringUtils.isEmpty(projectSn)) {
            return Result.requestParamError("projectSn is null");
        }
        Integer contractId = basicInformationParam.getContractId();
        if (contractId == null) {
            return Result.requestParamError("contractId is null");
        }
        Integer firstParty = basicInformationParam.getFirstParty();
        if (firstParty == null) {
            return Result.requestParamError("firstParty is null");
        }
        Integer secondParty = basicInformationParam.getSecondParty();
        if (secondParty == null) {
            return Result.requestParamError("secondParty is null");
        }
        String firstPartyLink = basicInformationParam.getFirstPartyLink();
        if (StringUtils.isEmpty(firstPartyLink)) {
            return Result.requestParamError("firstPartyLink is null");
        }
        String firstPartyMobile = basicInformationParam.getFirstPartyMobile();
        if (StringUtils.isEmpty(firstPartyMobile)) {
            return Result.requestParamError("firstPartyMobile is null");
        }
        String secondPartyLink = basicInformationParam.getSecondPartyLink();
        if (StringUtils.isEmpty(secondPartyLink)) {
            return Result.requestParamError("secondPartyLink is null");
        }
        String secondPartyMobile = basicInformationParam.getSecondPartyMobile();
        if (StringUtils.isEmpty(secondPartyMobile)) {
            return Result.requestParamError("secondPartyMobile is null");
        }
        Date startDate = basicInformationParam.getStartDate();
        if (startDate == null) {
            return Result.requestParamError("startDate is null");
        }
        Date endDate = basicInformationParam.getEndDate();
        if (endDate == null) {
            return Result.requestParamError("endDate is null");
        }
        String token = getToken();
        redisCache.setCacheObject(PROJECT_BASIC_INFO + token, basicInformationParam, 1, TimeUnit.DAYS);
        return Result.success();
    }

    /**
     * 添加甲方计价规则到缓存
     */
    @Override
    public Result addFeeRulePartyA(List<BpoFeeRuleParam> bpoFeeRuleParamList) {
        Result result = handleProjectConfigParam(bpoFeeRuleParamList);
        if (result != null) {
            return result;
        }
        String token = getToken();
        redisCache.setCacheObject(PROJECT_FEE_RULE_A + token, bpoFeeRuleParamList, 1, TimeUnit.DAYS);
        return Result.success();
    }

    /**
     * 将前端传过来的数据返回到前端
     */
    @Override
    public Result<BpoProjectCache> getCache() {
        String token = getToken();
        //获取项目信息
        BpoProject bpoProject = redisCache.getCacheObject(PROJECT_BASIC_INFO + token);
        ProjectConfigParam projectConfigParam = redisCache.getCacheObject(PROJECT_CONFIG + token);
        List<BpoFeeRuleParam> bpoFeeRulePartyAParamList = redisCache.getCacheObject(PROJECT_FEE_RULE_A + token);
//      List<BpoFeeRuleParam> bpoFeeRulePartyBParamList = redisCache.getCacheObject(PROJECT_FEE_RULE_B + token);
        ProjectSupplierParam projectSupplierParam = redisCache.getCacheObject(PROJECT_SUPPLIER + token);

        BpoProjectCache bpoProjectCache = new BpoProjectCache();
        bpoProjectCache.setBpoFeeRulePartyA(bpoFeeRulePartyAParamList);
//      bpoProjectCache.setBpoFeeRulePartyB(bpoFeeRulePartyBParamList);
        bpoProjectCache.setProject(bpoProject);
        bpoProjectCache.setProjectConfigParam(projectConfigParam);
        bpoProjectCache.setProjectSupplierParam(projectSupplierParam);

        Result<BpoProjectCache> result = new Result<>(REQUEST_SUCCESS);
        result.setData(bpoProjectCache);
        return result;
    }

    /**
     * 添加计价规则
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result addFeeRule(List<FeeRuleParam> addProductPriceParamList) {
        for (FeeRuleParam feeRuleParam : addProductPriceParamList) {
            Integer computationalType = feeRuleParam.getComputationalType();
            if (computationalType == null) {
                return Result.requestParamError("computationalType is null");
            }
            String unitPrice = feeRuleParam.getUnitPrice();
            if (StringUtils.isEmpty(unitPrice)) {
                return Result.requestParamError("unitPrice is null");
            }
            String ruleName = feeRuleParam.getRuleName();
            if (StringUtils.isEmpty(ruleName)) {
                return Result.requestParamError("ruleName is null");
            }
            Integer priorityLevel = feeRuleParam.getPriorityLevel();
            if (priorityLevel == null) {
                return Result.requestParamError("priorityLevel is null");
            }
            Date effectiveTime = feeRuleParam.getEffectiveTime();
            if (effectiveTime == null) {
                return Result.requestParamError("effectiveTime is null");
            }
            Date finishTime = feeRuleParam.getFinishTime();
            if (finishTime == null) {
                return Result.requestParamError("finishTime is null");
            }
            Integer type = feeRuleParam.getType();
            if (type == null) {
                return Result.requestParamError("type is null");
            }
            if (computationalType == BY_NUMBER) {
                BpoFeeRule bpoFeeRule = new BpoFeeRule();
                bpoFeeRule.setEffectiveTime(effectiveTime);
                bpoFeeRule.setFinishTime(finishTime);
                bpoFeeRule.setRuleName(ruleName);
                bpoFeeRule.setType(type);
                bpoFeeRule.setPriorityLevel(priorityLevel);
                bpoFeeRuleMapper.insertSelective(bpoFeeRule);
            } else if (computationalType == BY_TIME) {
                BpoFeeRuleByTime bpoFeeRuleByTime = new BpoFeeRuleByTime();
                bpoFeeRuleByTime.setType(type);
                bpoFeeRuleByTime.setUnitPrice(unitPrice);
                bpoFeeRuleByTime.setPriorityLevel(priorityLevel);
                bpoFeeRuleByTime.setEffectiveTime(effectiveTime);
                bpoFeeRuleByTime.setFinishTime(finishTime);
                bpoFeeRuleByTime.setRuleName(ruleName);
                bpoFeeRuleByTimeMapper.insertSelective(bpoFeeRuleByTime);
            } else {
                return Result.requestParamError("computationalType is invalid");
            }
        }

        return Result.success();
    }

    /**
     * 添加产品价格
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result addProductPrice(List<ProductPriceParam> addProductPriceParamList) {
        for (ProductPriceParam productPriceParam : addProductPriceParamList) {
            Integer feeRuleId = productPriceParam.getFeeRuleId();
            if (feeRuleId == null) {
                return Result.requestParamError("feeRuleId is null");
            }
            Integer processId = productPriceParam.getProcessId();
            if (processId == null) {
                return Result.requestParamError("processId is null");
            }
            Integer upperLimit = productPriceParam.getUpperLimit();
            if (upperLimit == null) {
                return Result.requestParamError("upperLimit is null");
            }
            Integer lowerLimit = productPriceParam.getLowerLimit();
            if (lowerLimit == null) {
                return Result.requestParamError("lowerLimit is null");
            }
            String unitPrice = productPriceParam.getUnitPrice();
            if (StringUtils.isEmpty(unitPrice)) {
                return Result.requestParamError("unitPrice is null");
            }
            Integer productId = productPriceParam.getProductId();
            BpoProductPrice bpoProductPrice = new BpoProductPrice();
            bpoProductPrice.setLowerLimit(lowerLimit);
            bpoProductPrice.setUpperLimit(upperLimit);
            bpoProductPrice.setPrice(unitPrice);

            String productName = productPriceParam.getProductName();
            if (productId != null && StringUtils.isEmpty(productName)) {
                Result.requestParamError();
            }
            byte productType = 1;
            if (productId == null && !StringUtils.isEmpty(productName)) {
                BpoProduct product = bpoProductMapper.findByNameAndProcessId(productName, processId);
                if (product == null || product.getId() == null) {
                    product = new BpoProduct();
                    product.setProductName(productName);
                    bpoProductMapper.insertSelective(product);
                }
                productType = 2;
                productId = product.getId();
            }
            bpoProductPrice.setProductId(productId);
            bpoProductPrice.setProductType(productType);
            bpoProductPrice.setProcessId(processId);
            bpoProductPrice.setFeeRuleId(processId);
            bpoProductPriceMapper.insertSelective(bpoProductPrice);
        }
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result deleteFeeRule(int id, int projectId) {
        if (id < 0) {
            return Result.requestParamError("id is invalid");
        }
        if (projectId < 0) {
            return Result.requestParamError("projectId is invalid");
        }
        ProjectComputationalFeeRuleShow projectComputationalFeeRuleShow = getProjectComputationalFeeRuleShow(projectId);
        List<BpoFeeRuleShow> feeRuleShowList = projectComputationalFeeRuleShow.getFeeRuleShowList();
        if (feeRuleShowList == null || feeRuleShowList.isEmpty()) {
            return Result.error("项目下不存在计费规则");
        }
        //匹配计价规则设置状态为删除,如果是计件设置相关工作模块,工作小组,工序,产品,产品价格数据状态为删除
        for (BpoFeeRuleShow bpoFeeRuleShow : feeRuleShowList) {
            Integer feeRuleId = bpoFeeRuleShow.getFeeRuleId();
            if (feeRuleId == null) {
                return Result.error();
            }
            if (feeRuleId == id) {
                BpoFeeRule feeRule = new BpoFeeRule();
                feeRule.setStatus(DELETE_STATUS);
                feeRule.setId(id);
                bpoFeeRuleMapper.updateByPrimaryKeySelective(feeRule);
                Integer computationalType = bpoFeeRuleShow.getComputationalType();
                if (computationalType == BY_NUMBER) {
                    List<BpoFeeRuleModelShow> feeRuleModelShowList = bpoFeeRuleShow.getFeeRuleModelShowList();
                    for (BpoFeeRuleModelShow bpoFeeRuleModelShow : feeRuleModelShowList) {
                        BpoWorkModel bpoWorkModel = new BpoWorkModel();
                        bpoWorkModel.setId(bpoFeeRuleModelShow.getModelId());
                        bpoWorkModel.setStatus(DELETE_STATUS);
                        workModelMapper.updateByPrimaryKeySelective(bpoWorkModel);
                        List<BpoFeeRuleGroupShow> feeRuleGroupShows = bpoFeeRuleModelShow.getFeeRuleGroupShows();
                        for (BpoFeeRuleGroupShow feeRuleGroupShow : feeRuleGroupShows) {
                            BpoWorkGroup bpoWorkGroup = new BpoWorkGroup();
                            bpoWorkModel.setId(feeRuleGroupShow.getGroupId());
                            bpoWorkModel.setStatus(DELETE_STATUS);
                            workGroupMapper.updateByPrimaryKeySelective(bpoWorkGroup);
                            List<BpoFeeRuleProcessShow> feeRuleProcessShowList = feeRuleGroupShow.getFeeRuleProcessShowList();
                            for (BpoFeeRuleProcessShow bpoFeeRuleProcessShow : feeRuleProcessShowList) {
                                BpoProcess bpoProcess = new BpoProcess();
                                bpoWorkModel.setId(bpoFeeRuleProcessShow.getProcessId());
                                bpoWorkModel.setStatus(DELETE_STATUS);
                                processMapper.updateByPrimaryKeySelective(bpoProcess);
                                List<BpoFeeRulePriceShow> feeRulePriceShowList = bpoFeeRuleProcessShow.getFeeRulePriceShowList();
                                List<Integer> deletedProductId = new ArrayList<>();
                                for (BpoFeeRulePriceShow bpoFeeRulePriceShow : feeRulePriceShowList) {
                                    BpoProductPrice bpoProductPrice = new BpoProductPrice();
                                    bpoProductPrice.setId(bpoFeeRulePriceShow.getPriceId());
                                    bpoProductPrice.setStatus((byte) DELETE_STATUS);
                                    bpoProductPriceMapper.updateByPrimaryKeySelective(bpoProductPrice);
                                    Integer productId = bpoFeeRulePriceShow.getProductId();
                                    if (deletedProductId.indexOf(productId) == -1) {
                                        BpoProduct bpoProduct = new BpoProduct();
                                        bpoProduct.setId(productId);
                                        bpoProduct.setStatus(DELETE_STATUS);
                                        bpoProductMapper.updateByPrimaryKeySelective(bpoProduct);
                                        deletedProductId.add(productId);
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                if (computationalType == BY_TIME) {
                    break;
                }
            }
        }

        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result deleteProductPrice(int[] ids) {
        if (ids.length == 0) {
            return Result.requestParamError("id is invalid");
        }
        for (int id : ids) {
            BpoProductPrice feeRule = new BpoProductPrice();
            feeRule.setStatus((byte) DELETE_STATUS);
            feeRule.setId(id);
            bpoProductPriceMapper.updateByPrimaryKeySelective(feeRule);
        }
        return Result.success();
    }

    @Override
    public Result<BpoPartyAListShow> getPartyAList() {
        List<SysCompany> sysCompanyList = sysCompanyMapper.findCompanyByOrgType(SYS_ORG_JF, SYS_ORG_JF_STRING);
        Result<BpoPartyAListShow> result = new Result<>(REQUEST_SUCCESS);
        BpoPartyAListShow bpoPartyAListShow = new BpoPartyAListShow();
        bpoPartyAListShow.setCompanyList(sysCompanyList);
        result.setData(bpoPartyAListShow);
        return result;
    }

    @Override
    public Result<BpoSupplierShow> getSupplierList() {
        List<SysCompany> sysCompanyList = sysCompanyMapper.findCompanyByOrgType(SYS_ORG_PROVIDER, SYS_ORG_PROVIDER_STRING);
        Result<BpoSupplierShow> result = new Result<>(REQUEST_SUCCESS);
        BpoSupplierShow bpoSupplierShow = new BpoSupplierShow();
        bpoSupplierShow.setSysCompanyList(sysCompanyList);
        result.setData(bpoSupplierShow);
        return result;
    }

    /**
     * 添加供应商信息到缓存
     */
    @Override
    public Result addProjectSupplier(ProjectSupplierParam projectSupplierParam) {
        Result result = handleProjectSupplierParam(projectSupplierParam);
        if (result != null) {
            return result;
        }
        String token = getToken();
        redisCache.setCacheObject(PROJECT_SUPPLIER + token, projectSupplierParam, 1, TimeUnit.DAYS);

        return Result.success();
    }

    @Override
    public Result<BpoSupplierListRelatedShow> getSupplierListRelatedByProjectId(int projectId) {
        if (projectId < 0) {
            return Result.requestParamError("projectId is invalid");
        }
        Integer companyId = getCurrentUserCompanyId();
        BpoSupplierListRelatedShow bpoSupplierListRelatedShow = bpoSupplierInfoMapper.findSupplierByProjectId(projectId,companyId);
        Result<BpoSupplierListRelatedShow> relatedShowResult = new Result<>(REQUEST_SUCCESS);
        relatedShowResult.setData(bpoSupplierListRelatedShow);
        return relatedShowResult;
    }

    private Integer getCurrentUserCompanyId() {
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();
        SysCompany companyByUserId = sysCompanyMapper.findCompanyByUserId(userId);
        return companyByUserId.getId();
    }

    @Override
    public Result<List<SysCompany>> getSupplierListUnrelated(int projectId) {
        if (projectId < 0) {
            return Result.requestParamError("projectId is invalid");
        }
        List<SysCompany> sysCompanyList = sysCompanyMapper.findCompanyByOrgType(SYS_ORG_PROVIDER, SYS_ORG_PROVIDER_STRING);
        Integer companyId = getCurrentUserCompanyId();
        BpoSupplierListRelatedShow bpoSupplierListRelatedShow = bpoSupplierInfoMapper.findSupplierByProjectId(projectId,companyId);
        List<SysCompany> removeSysCompany = new ArrayList<>();
        for (SysCompany sysCompany : sysCompanyList) {
            Integer sysCompanyId = sysCompany.getId();
            for (FirstLevelSupplierShow firstLevelSupplierShow : bpoSupplierListRelatedShow.getFirstLevelSupplierParamList()) {
                Integer firstLevelSupplierShowId = firstLevelSupplierShow.getId();
                if (sysCompanyId.equals(firstLevelSupplierShowId)) {
                    removeSysCompany.add(sysCompany);
                }
                for (SecondLevelSupplierShow secondLevelSupplierShow : firstLevelSupplierShow.getSecondLevelSupplierShows()) {
                    Integer secondLevelSupplierShowId = secondLevelSupplierShow.getId();
                    if (sysCompanyId.equals(secondLevelSupplierShowId)) {
                        removeSysCompany.add(sysCompany);
                    }
                }
            }
        }
        sysCompanyList.removeAll(removeSysCompany);
        Result<List<SysCompany>> relatedShowResult = new Result<>(REQUEST_SUCCESS);
        relatedShowResult.setData(sysCompanyList);
        return relatedShowResult;
    }


    @Override
    public Result<NameOrCodeExistsShow> isNameOrCodeExists(String name, String code) {
        NameOrCodeExistsShow nameOrCodeExistsShow = new NameOrCodeExistsShow();
        boolean isNameExists = true;
        boolean isCodeExists = true;
        SysWorkModuleRel sysWorkModuleRel;
        if (StringUtils.isEmpty(name)) {
            isNameExists = false;
        } else {
            sysWorkModuleRel = sysWorkModuleRelMapper.isExistSameName(name);
            if (sysWorkModuleRel == null) {
                isNameExists = false;
            }
        }
        if (StringUtils.isEmpty(code)) {
            isCodeExists = false;
        } else {
            sysWorkModuleRel = sysWorkModuleRelMapper.isExistSameCode(code);
            if (sysWorkModuleRel == null) {
                isCodeExists = false;
            }
        }
        Result<NameOrCodeExistsShow> result = new Result<>(REQUEST_SUCCESS);
        nameOrCodeExistsShow.setIsCodeExists(isCodeExists);
        nameOrCodeExistsShow.setIsNameExists(isNameExists);
        result.setData(nameOrCodeExistsShow);
        return result;
    }

    @Override
    public Result<SysCompany> getUserCompany() {
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();
        SysCompany companyByUserId = sysCompanyMapper.findCompanyByUserId(userId);
        Result<SysCompany> result = new Result<>(REQUEST_SUCCESS);
        result.setData(companyByUserId);
        return result;
    }
    @Override
    public Result<List<BpoProjectDetailListShow>> getProjectDetailList(Integer transferStationId) {
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();
        SysCompany companyByUserId = sysCompanyMapper.findCompanyByUserId(userId);


        List<BpoProjectDetailListShow> bpoProjectDetailListShows = projectMapper.findProjectByOrgId(companyByUserId.getId(),transferStationId);
        // TODO:  2020/6/29  添加项目考勤排班信息


        Result<List<BpoProjectDetailListShow>> result = new Result<>(REQUEST_SUCCESS);
        result.setData(bpoProjectDetailListShows);
        return result;
    }

    @Override
    public Result getAllByLoginUserId() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Integer userId = loginUser.getUser().getUserId();
        List<BpoProjectSimpleList> list = projectMapper.getAllByLoginUserId(userId);
        return Result.success(list);
    }

    @Override
    public Result getList(BpoProjectListShow param, Page<BpoProjectListShow> page) {

        LoginUser loginUser = SecurityUtils.getLoginUser();
        Integer userId = loginUser.getUser().getUserId();

        PageHelper.startPage (page.getPageNum (),page.getPageSize ());

        List<BpoProjectListShow> list = projectMapper.getList(param,userId);

        //填充部门信息
        for(BpoProjectListShow info:list){
            if(info.getDeptId()!=null)
                //通过部门Id获取部门层级
                info.setSysDeptShow(sysDeptService.getDeptAll(info.getDeptId()));
        }

        PageInfo<BpoProjectListShow> pageInfo = new PageInfo<> (list);
        page.setList (list);
        page.setTotal (pageInfo.getTotal ());
        page.setPages (pageInfo.getPages ());

        return Result.success(page);
    }

    @Override
    public Result delProject(Integer projectId) {

        // TODO: 2020/7/21 0021 删除该项目管理与对应供应商的关联关系
        // TODO: 2020/7/21 0021 删除该项目的计费规则
        // TODO: 2020/7/21 0021 删除该项目的考核内容
        // 删除该项目
        int temp = bpoProductMapper.delPorjectById(projectId);

        return temp>0?Result.success():Result.error();
    }

    @Override
    public Result getProjectSnToCreate(BpoProjectSnParam bpoProjectSnParam) {
        //甲方拼音缩写_乙方拼音缩写_中转场缩写_年月日_序号,
        String partyACode = ChineseCharacterUtil.getUpperCase(bpoProjectSnParam.getPartyAName(), false);
        String partyBCode = ChineseCharacterUtil.getUpperCase(bpoProjectSnParam.getPartyBName(), false);
//        String transferStationCode = ChineseCharacterUtil.getUpperCase(bpoProjectSnParam.getTransferStationName(), false);
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int monthValue = localDate.getMonthValue();
        int dayOfMonth = localDate.getDayOfMonth();
        StringBuilder stringBuilder = new StringBuilder();
        String yearString = getStringFromNumber(year, 4);
        String monthString = getStringFromNumber(monthValue, 2);
        String dayString = getStringFromNumber(dayOfMonth, 2);
        Integer projectNumber = redisCache.getCacheObject(PROJECT_NUMBER + yearString);
        if (projectNumber == null || projectNumber < 0) {
            projectNumber = 1;
            redisCache.setCacheObject(PROJECT_NUMBER + yearString, projectNumber, 366, TimeUnit.DAYS);
        } else {
            redisCache.setCacheObject(PROJECT_NUMBER + yearString, projectNumber + 1, 366, TimeUnit.DAYS);
        }
        String projectNumberString = getStringFromNumber(projectNumber, 6);
        //甲方拼音缩写_乙方拼音缩写_中转场缩写_年月日_序号,
        //现在中转场不要
        stringBuilder
                .append(partyACode)
                .append("_")
                .append(partyBCode)
//                .append("_")
//                .append(transferStationCode)
                .append("_")
                .append(yearString).append(monthString).append(dayString)
                .append("_")
                .append(projectNumberString);
        return Result.success(stringBuilder.toString());
    }

    @Override
    public Result<List<BpoTransferStation>> getTransferStationByDistrictId(String districtId) {
        Integer sysAreaId = sysAreaRelMapper.getSysAreaIdByDistrictId(districtId);
        List<BpoTransferStation> allByCityId = bpoTransferStationMapper.findAllByCityId(sysAreaId);
        Result<List<BpoTransferStation>> result = new Result<>(REQUEST_SUCCESS);
        result.setData(allByCityId);
        return result;
    }

    @Override
    public Result<BpoContractInfoShow> getContractListByPartyAAndPartyB(int partyAId, int partyBId) {
        List<BpoContractInfo> contractInfoByPartyAIdAndPartyBId = bpoContractInfoMapper.findContractInfoByPartyAIdAndPartyBId(partyAId, partyBId);

        BpoContractInfoShow bpoContractInfoShow = new BpoContractInfoShow();
        bpoContractInfoShow.setBpoContractInfoList(contractInfoByPartyAIdAndPartyBId);

        Result<BpoContractInfoShow> result = new Result<>(REQUEST_SUCCESS);
        result.setData(bpoContractInfoShow);
        return result;
    }

    @Override
    public Result<BpoProjectDetailAllShow> detailAll(int id) {
        BpoProjectDetailAllShow bpoProjectDetailAllShow = new BpoProjectDetailAllShow();

        //项目信息
        BpoProjectDetailShow projectDetail = getBpoProjectDetailShow(id);
        Integer companyId = getCurrentUserCompanyId();
        //项目关联供应商列表
        BpoSupplierListRelatedShow bpoSupplierListRelatedShow = bpoSupplierInfoMapper.findSupplierByProjectId(id,companyId);

        //项目配置 工作模块-工序-工作小组
        ProjectConfigShow projectConfig = projectMapper.getProjectConfig(id);

        //获取项目计算费用规则
        ProjectComputationalFeeRuleShow computationalFeeRule = getProjectComputationalFeeRuleShow(id);

        //数据拼接
        bpoProjectDetailAllShow.setBpoProjectDetailShow(projectDetail);
        bpoProjectDetailAllShow.setBpoSupplierListRelatedShow(bpoSupplierListRelatedShow);
        bpoProjectDetailAllShow.setProjectComputationalFeeRuleShow(computationalFeeRule);
        bpoProjectDetailAllShow.setProjectConfigShow(projectConfig);

        Result<BpoProjectDetailAllShow> result = new Result<>(REQUEST_SUCCESS);
        result.setData(bpoProjectDetailAllShow);
        return result;
    }

    private String getStringFromNumber(int monthValue, int length) {
        StringBuilder s = new StringBuilder(String.valueOf(monthValue));
        while (s.length() < length) {
            s.insert(0, "0");
        }
        return s.toString();
    }

    private Result handleProjectSupplierParam(ProjectSupplierParam projectSupplierParam) {
        List<FirstLevelSupplierParam> firstLevelSupplierParamList = projectSupplierParam.getFirstLevelSupplierParamList();
        if (firstLevelSupplierParamList == null || firstLevelSupplierParamList.isEmpty()) {
            return Result.requestParamError("firstLevelSupplierParamList is null");
        }
        for (FirstLevelSupplierParam firstLevelSupplierParam : firstLevelSupplierParamList) {
            Integer firstLevelSupplierId = firstLevelSupplierParam.getFirstLevelSupplierId();
            if (firstLevelSupplierId == null) {
                return Result.requestParamError("firstLevelSupplierId is null");
            }
            String firstLevelSupplierName = firstLevelSupplierParam.getFirstLevelSupplierName();
            if (StringUtils.isEmpty(firstLevelSupplierName)) {
                return Result.requestParamError("firstLevelSupplierName is null");
            }
            List<SecondLevelSupplierParam> secondLevelSupplierParamList = firstLevelSupplierParam.getSecondLevelSupplierParamList();
            for (SecondLevelSupplierParam secondLevelSupplierParam : secondLevelSupplierParamList) {
                Integer secondLevelSupplierId = secondLevelSupplierParam.getSecondLevelSupplierId();
                if (secondLevelSupplierId == null) {
                    return Result.requestParamError("secondLevelSupplierId is null");
                }
                String secondLevelSupplierName = secondLevelSupplierParam.getSecondLevelSupplierName();
                if (StringUtils.isEmpty(secondLevelSupplierName)) {
                    return Result.requestParamError("secondLevelSupplierName is null");
                }
            }
        }
        return null;
    }

    @Override
    public Result<BpoContractInfoShow> getContractList(int partyAId) {
        if (partyAId < 0) {
            return Result.requestParamError("partyAId is invalid");
        }

        LoginUser loginUser = SecurityUtils.getLoginUser();
        Integer userId = loginUser.getUser().getUserId();
        SysCompany sysCompany = sysCompanyMapper.findCompanyByUserId(userId);
        Integer sysCompanyId = sysCompany.getId();


        //通过甲方Id和乙方Id查找合同信息
        List<BpoContractInfo> bpoContractInfoList =
                bpoContractInfoMapper.findContractInfoByPartyAIdAndPartyBId(partyAId, sysCompanyId);


        BpoContractInfoShow bpoContractInfoShow = new BpoContractInfoShow();
        bpoContractInfoShow.setBpoContractInfoList(bpoContractInfoList);

        Result<BpoContractInfoShow> result = new Result<>(REQUEST_SUCCESS);
        result.setData(bpoContractInfoShow);
        return result;
    }

//    @Override
//    @Transactional
//    public Result addFeeRulePartyB(List<BpoFeeRuleParam> bpoFeeRulePartyBParamList) {
//        Result result = checkBpoFeeRuleParam(bpoFeeRulePartyBParamList);
//        if (result != null) {
//            return result;
//        }
//        String token = getToken();
//        redisCache.setCacheObject(PROJECT_FEE_RULE_B + token, bpoFeeRulePartyBParamList, 1, TimeUnit.DAYS);
//
//
//        return Result.success();
//    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result createProject() {
        String token = getToken();
        //获取项目信息
        ProjectBasicInformationParam bpoProject = redisCache.getCacheObject(PROJECT_BASIC_INFO + token);
        //添加项目到数据库
        projectMapper.insertSelective(bpoProject);
        Integer secondParty = bpoProject.getSecondParty();

        BpoSupplierInfo bpoSupplierInfo = new BpoSupplierInfo();
        bpoSupplierInfo.setOrgId(secondParty);
        bpoSupplierInfo.setParentId(-1);
        bpoSupplierInfoMapper.insertSelective(bpoSupplierInfo);
        //获取项目配置信息
        ProjectConfigParam projectConfigParam = redisCache.getCacheObject(PROJECT_CONFIG + token);
        Integer projectId = bpoProject.getId();
        projectConfigParam.setProjectId(projectId);
        //添加配置信息到数据库
        //返回系统配置和工序的关联信息
        Map<Integer, Integer> sysIdProcessIds = this.addProjectConfigToDB(projectConfigParam);
        //获取项目甲方计价信息
        List<BpoFeeRuleParam> bpoFeeRulePartyAParamList = redisCache.getCacheObject(PROJECT_FEE_RULE_A + token);
//        List<BpoFeeRuleParam> bpoFeeRulePartyBParamList = redisCache.getCacheObject(PROJECT_FEE_RULE_B + token);
        //添加项目计价信息到数据库
        this.addFeeRuleToDB(bpoFeeRulePartyAParamList, sysIdProcessIds, projectId);
//        addFeeRuleToDB(bpoFeeRulePartyBParamList, sysIdProcessIds, projectId);
        //获取供应商信息并添加到数据库
        ProjectSupplierParam projectSupplierParam = redisCache.getCacheObject(PROJECT_SUPPLIER + token);
        projectSupplierParam.setProjectId(projectId);
        this.addProjectSupplierToDB(projectSupplierParam);

        return Result.success();
    }

    private void addProjectSupplierToDB(ProjectSupplierParam projectSupplierParam) {
        Integer projectId = projectSupplierParam.getProjectId();
        List<FirstLevelSupplierParam> firstLevelSupplierParamList = projectSupplierParam.getFirstLevelSupplierParamList();
        for (FirstLevelSupplierParam firstLevelSupplierParam : firstLevelSupplierParamList) {
            Integer firstLevelSupplierId = firstLevelSupplierParam.getFirstLevelSupplierId();
//            String firstLevelSupplierName = firstLevelSupplierParam.getFirstLevelSupplierName();

            BpoSupplierInfo bpoSupplierInfo = new BpoSupplierInfo();
            bpoSupplierInfo.setContractId(firstLevelSupplierParam.getPartyBToSupplierContractId());
            bpoSupplierInfo.setOrgId(firstLevelSupplierId);
            bpoSupplierInfo.setParentId(-1);
            bpoSupplierInfo.setProjectId(projectId);
            bpoSupplierInfo.setIsAgencySalary(firstLevelSupplierParam.getFirstLevelIsAgencySalary().byteValue());
            bpoSupplierInfoMapper.insertSelective(bpoSupplierInfo);

            List<SecondLevelSupplierParam> secondLevelSupplierParamList = firstLevelSupplierParam.getSecondLevelSupplierParamList();
            if (secondLevelSupplierParamList != null && !secondLevelSupplierParamList.isEmpty()) {
                for (SecondLevelSupplierParam secondLevelSupplierParam : secondLevelSupplierParamList) {
                    Integer secondLevelSupplierId = secondLevelSupplierParam.getSecondLevelSupplierId();
//                    String secondLevelSupplierName = secondLevelSupplierParam.getSecondLevelSupplierName();
                    bpoSupplierInfo = new BpoSupplierInfo();
                    bpoSupplierInfo.setContractId(secondLevelSupplierParam.getSupplierToSupplierContractId());
                    bpoSupplierInfo.setOrgId(secondLevelSupplierId);
                    bpoSupplierInfo.setParentId(firstLevelSupplierId);
                    bpoSupplierInfo.setProjectId(projectId);
                    bpoSupplierInfo.setIsAgencySalary(secondLevelSupplierParam.getSecondLevelIsAgencySalary().byteValue());
                    bpoSupplierInfoMapper.insertSelective(bpoSupplierInfo);
                }
            }
        }
    }

    private void addFeeRuleToDB(List<BpoFeeRuleParam> bpoFeeRulePartyBParamList, Map<Integer, Integer> sysIdProcessIds, Integer projectId) {
        for (BpoFeeRuleParam bpoFeeRuleParam : bpoFeeRulePartyBParamList) {
            Date effectiveTime = bpoFeeRuleParam.getEffectiveTime();
            Date finishTime = bpoFeeRuleParam.getFinishTime();
            Integer priorityLevel = bpoFeeRuleParam.getPriorityLevel();
            Integer type = bpoFeeRuleParam.getType();
            String unitPrice = bpoFeeRuleParam.getUnitPrice();
            Integer computationalType = bpoFeeRuleParam.getComputationalType();
            String ruleName = bpoFeeRuleParam.getRuleName();
            if (computationalType == BY_NUMBER) {
                //添加计价规则表
                BpoFeeRule bpoFeeRule = new BpoFeeRule();
                bpoFeeRule.setEffectiveTime(effectiveTime);
                bpoFeeRule.setFinishTime(finishTime);
                bpoFeeRule.setRuleName(ruleName);
                bpoFeeRule.setType(type);
                bpoFeeRule.setPriorityLevel(priorityLevel);
                bpoFeeRuleMapper.insertSelective(bpoFeeRule);
                Integer bpoFeeRuleId = bpoFeeRule.getId();
                List<BpoProcessParam> bpoProcessParams = bpoFeeRuleParam.getBpoProcessParams();
                for (BpoProcessParam process : bpoProcessParams) {

                    Integer sysWorkModuleRelId = process.getSysProcessId();
                    List<BpoProductParam> bpoProductParams = process.getBpoProductParams();
                    Integer processId = sysIdProcessIds.get(sysWorkModuleRelId);

                    Set<String> hashSet = new HashSet<>();
                    Integer productId = null;
                    for (BpoProductParam bpoProductParam : bpoProductParams) {
                        String productName = bpoProductParam.getProductName();

                        if (!StringUtils.isEmpty(productName) && !hashSet.contains(productName)) {
                            BpoProduct product = new BpoProduct();
                            product.setProcessId(processId);
                            product.setProductName(productName);
                            product.setStatus(NORMAL_STATUS);
                            bpoProductMapper.insertSelective(product);
                            hashSet.add(productName);
                            productId = product.getId();
                        }
                        BpoProductPrice bpoPrice = new BpoProductPrice();
                        bpoPrice.setProductId(productId);
                        bpoPrice.setPrice(bpoProductParam.getUnitPrice());
                        bpoPrice.setUpperLimit(bpoProductParam.getUpperLimit());
                        bpoPrice.setFeeRuleId(bpoFeeRuleId);
                        bpoPrice.setProductType((byte) 2);
                        bpoPrice.setLowerLimit(bpoProductParam.getLowerLimit());
                        bpoPrice.setProcessId(processId);
                        bpoPrice.setType(type);
                        bpoProductPriceMapper.insertSelective(bpoPrice);
                    }
                }
            } else if (computationalType == BY_TIME) {
                BpoFeeRuleByTime bpoFeeRuleByTime = new BpoFeeRuleByTime();
                bpoFeeRuleByTime.setType(type);
                bpoFeeRuleByTime.setUnitPrice(unitPrice);
                bpoFeeRuleByTime.setProjectId(projectId);
                bpoFeeRuleByTime.setPriorityLevel(priorityLevel);
                bpoFeeRuleByTime.setEffectiveTime(effectiveTime);
                bpoFeeRuleByTime.setFinishTime(finishTime);
                bpoFeeRuleByTime.setRuleName(ruleName);
                bpoFeeRuleByTimeMapper.insertSelective(bpoFeeRuleByTime);
            }
        }
    }

    private Map<Integer, Integer> addProjectConfigToDB(ProjectConfigParam projectConfigParam) {
        Integer projectId = projectConfigParam.getProjectId();
        Integer transferStationId = projectConfigParam.getTransferStationId();
        // 项目添加中转场关联信息
        BpoProject bpoProject = new BpoProject();
        bpoProject.setId(projectId);
        bpoProject.setTransferStationId(transferStationId);

        projectMapper.updateByPrimaryKeySelective(bpoProject);
        List<BpoWorkModelParam> workModels = projectConfigParam.getWorkModels();
        Map<Integer, Integer> sysIdProcessIds = new HashMap<>();

        for (BpoWorkModelParam bpoWorkModelParam : workModels) {
            //查询系统设置表模块信息
            Integer sysWorkModuleRelId = bpoWorkModelParam.getSysModuleId();
            SysWorkModuleRel moduleRel = getSysWorkModuleRel(sysWorkModuleRelId);
            //添加模块到数据库
            String moduleName = moduleRel.getName();
            BpoWorkModel bpoWorkModel = new BpoWorkModel();
            bpoWorkModel.setModelName(moduleName);
            bpoWorkModel.setModuleCode(moduleRel.getCode());
            bpoWorkModel.setProjectId(projectId);
            bpoWorkModel.setStatus(DictDataConstants.NORMAL_STATUS);
            bpoWorkModel.setSysWorkModuleRelId(sysWorkModuleRelId);
            workModelMapper.insertSelective(bpoWorkModel);
            Integer workModelId = bpoWorkModel.getId();

            List<BpoGroupParam> groupSysWorkModuleRelIds = bpoWorkModelParam.getBpoGroupParamList();
            for (BpoGroupParam groupParam : groupSysWorkModuleRelIds) {
                //查询系统设置表  工作小组信息
                Integer sysGroupId = groupParam.getSysGroupId();
                SysWorkModuleRel groupSysWorkModuleRel = getSysWorkModuleRel(sysGroupId);
                //添加工作小组到数据库
                String groupName = groupSysWorkModuleRel.getName();
                BpoWorkGroup workGroup = new BpoWorkGroup();
                workGroup.setGroupName(groupName);
                workGroup.setStatus(DictDataConstants.NORMAL_STATUS);
                workGroup.setGroupCode(groupSysWorkModuleRel.getCode());
                workGroup.setWorkModelId(workModelId);
                workGroup.setSysWorkModuleRelId(sysGroupId);
                workGroupMapper.insertSelective(workGroup);
                Integer workGroupId = workGroup.getId();
                List<BpoProcessParam> processes = groupParam.getProcesses();
                for (BpoProcessParam process : processes) {
                    //查询系统设置表  工序信息
                    Integer processSysWorkModuleRelId = process.getSysProcessId();
                    SysWorkModuleRel processSysWorkModuleRel = getSysWorkModuleRel(processSysWorkModuleRelId);
                    //添加工序到数据库
                    String processName = processSysWorkModuleRel.getName();
                    BpoProcess bpoProcess = new BpoProcess();
                    bpoProcess.setProcessName(processName);
                    bpoProcess.setSysWorkModuleRelId(processSysWorkModuleRelId);
                    bpoProcess.setProcessCode(processSysWorkModuleRel.getCode());
                    bpoProcess.setWorkGroupId(workGroupId);
                    bpoProcess.setStatus(DictDataConstants.NORMAL_STATUS);
                    processMapper.insertSelective(bpoProcess);
                    Integer processId = bpoProcess.getId();
                    sysIdProcessIds.put(processSysWorkModuleRelId, processId);
                }
            }
        }
        return sysIdProcessIds;
    }

    private Result handleProjectConfigParam(List<BpoFeeRuleParam> bpoFeeRuleParamList) {
        if (bpoFeeRuleParamList == null || bpoFeeRuleParamList.isEmpty()) {
            return Result.requestParamError();
        }
        for (BpoFeeRuleParam bpoFeeRuleParam : bpoFeeRuleParamList) {
            Integer computationalType = bpoFeeRuleParam.getComputationalType();
            if (computationalType == null) {
                return Result.requestParamError("computationalType is null");
            }
            String unitPrice = bpoFeeRuleParam.getUnitPrice();
            if (StringUtils.isEmpty(unitPrice)) {
                return Result.requestParamError("unitPrice is null");
            }
            String ruleName = bpoFeeRuleParam.getRuleName();
            if (StringUtils.isEmpty(ruleName)) {
                return Result.requestParamError("ruleName is null");
            }
            Integer priorityLevel = bpoFeeRuleParam.getPriorityLevel();
            if (priorityLevel == null) {
                return Result.requestParamError("priorityLevel is null");
            }
            Date effectiveTime = bpoFeeRuleParam.getEffectiveTime();
            if (effectiveTime == null) {
                return Result.requestParamError("effectiveTime is null");
            }
            Date finishTime = bpoFeeRuleParam.getFinishTime();
            if (finishTime == null) {
                return Result.requestParamError("finishTime is null");
            }
            Integer type = bpoFeeRuleParam.getType();
            if (type == null) {
                return Result.requestParamError("type is null");
            }
            if (bpoFeeRuleParam.getType() == BY_NUMBER) {
                List<BpoProcessParam> bpoProcessParams = bpoFeeRuleParam.getBpoProcessParams();
                if (bpoProcessParams == null || bpoProcessParams.isEmpty()) {
                    return Result.requestParamError("bpoProcessParams is null or empty");
                }
                for (BpoProcessParam bpoProcessParam : bpoProcessParams) {
                    Integer sysProcessId = bpoProcessParam.getSysProcessId();
                    if (sysProcessId == null) {
                        return Result.requestParamError("sysProcessId is null");
                    }
                    List<BpoProductParam> bpoProductParams = bpoProcessParam.getBpoProductParams();
                    if (bpoProductParams == null || bpoProductParams.isEmpty()) {
                        return Result.requestParamError("bpoProductParams is null or empty");
                    }
                    for (BpoProductParam bpoProductParam : bpoProductParams) {
                        String productName = bpoProductParam.getProductName();
                        if (StringUtils.isEmpty(productName)) {
                            return Result.requestParamError("productName is null");
                        }
                        Integer upperLimit = bpoProductParam.getUpperLimit();
                        if (upperLimit == null) {
                            return Result.requestParamError("upperLimit is null");
                        }
                        Integer lowerLimit = bpoProductParam.getLowerLimit();
                        if (lowerLimit == null) {
                            return Result.requestParamError("lowerLimit is null");
                        }
                        Boolean directly = bpoProductParam.getDirectly();
                        if (directly == null) {
                            return Result.requestParamError("directly is null");
                        }

                        String productParamUnitPrice = bpoProductParam.getUnitPrice();
                        if (StringUtils.isEmpty(productParamUnitPrice)) {
                            return Result.requestParamError("productParamUnitPrice is null");
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Result<ProjectConfigShow> getProjectConfig(int id) {
        ProjectConfigShow projectConfig = projectMapper.getProjectConfig(id);
        Result<ProjectConfigShow> result = new Result<>(REQUEST_SUCCESS);
        result.setData(projectConfig);
        return result;
    }

    @Override
    public List<ProjectConfigListShow> getProjectConfigList(int id) {
        List<ProjectConfigListShow> list = projectMapper.getProjectConfigList(id);
        return list;
    }

    @Override
    public Result<ProjectComputationalFeeRuleShow> getComputationalFeeRule(int id) {
        ProjectComputationalFeeRuleShow computationalFeeRule = getProjectComputationalFeeRuleShow(id);
        Result<ProjectComputationalFeeRuleShow> result = new Result<>(REQUEST_SUCCESS);
        result.setData(computationalFeeRule);
        return result;
    }

    private ProjectComputationalFeeRuleShow getProjectComputationalFeeRuleShow(int id) {

        // TODO: 2020/6/28 0028 项目有计价规则字段未使用
        ProjectComputationalFeeRuleShow computationalFeeRule = projectMapper.getComputationalFeeRuleByTime(id);
        //如果按时间计价为空,查询按件计费
        if (computationalFeeRule == null) {
            computationalFeeRule = projectMapper.getComputationalFeeRuleByNumber(id);
        }
        return computationalFeeRule;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result updateProductPrice(List<ProductPriceParam> productPriceParamList) {
        for (ProductPriceParam productPriceParam : productPriceParamList) {
            Integer priceId = productPriceParam.getPriceId();
            if (priceId == null) {
                return Result.requestParamError("priceId is null");
            }
            Integer upperLimit = productPriceParam.getUpperLimit();
            if (upperLimit == null) {
                return Result.requestParamError("upperLimit is null");
            }
            Integer lowerLimit = productPriceParam.getLowerLimit();
            if (lowerLimit == null) {
                return Result.requestParamError("lowerLimit is null");
            }
            String unitPrice = productPriceParam.getUnitPrice();
            if (StringUtils.isEmpty(unitPrice)) {
                return Result.requestParamError("unitPrice is null");
            }
            BpoProductPrice bpoProductPrice = new BpoProductPrice();
            bpoProductPrice.setId(priceId);
            bpoProductPrice.setLowerLimit(lowerLimit);
            bpoProductPrice.setUpperLimit(upperLimit);
            bpoProductPrice.setPrice(unitPrice);
            bpoProductPriceMapper.updateByPrimaryKeySelective(bpoProductPrice);
            Integer productId = productPriceParam.getProductId();
            if (productId != null) {
                String productName = productPriceParam.getProductName();
                if (StringUtils.isEmpty(productName)) {
                    return Result.requestParamError("productName is null");
                }
                BpoProduct product = new BpoProduct();
                product.setId(productId);
                product.setProductName(productName);
                bpoProductMapper.updateByPrimaryKeySelective(product);
            }
        }
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result updateProjectLink(ProjectBasicInformationParam projectBasicInformationParam) {
        BpoProject bpoProject = new BpoProject();
        bpoProject.setId(projectBasicInformationParam.getId());
        bpoProject.setFirstPartyMobile(projectBasicInformationParam.getFirstPartyLink());
        bpoProject.setFirstPartyLink(projectBasicInformationParam.getFirstPartyMobile());
        bpoProject.setSecondPartyLink(projectBasicInformationParam.getSecondPartyLink());
        bpoProject.setSecondPartyMobile(projectBasicInformationParam.getSecondPartyMobile());
        projectMapper.updateByPrimaryKeySelective(bpoProject);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result addSupplier(BpoSupplierInfo bpoSupplierInfo) {
        Integer orgId = bpoSupplierInfo.getOrgId();
        if (orgId == null) {
            return Result.requestParamError("orgId is null");
        }
        Integer projectId = bpoSupplierInfo.getProjectId();
        if (projectId == null) {
            return Result.requestParamError("projectId is null");
        }
//        Integer contractId = bpoSupplierInfo.getContractId();
//        if (contractId == null) {
//            return Result.requestParamError("contractId is null");
//        }
        Integer parentId = bpoSupplierInfo.getParentId();
        if (parentId == null) {
            return Result.requestParamError("parentId is null");
        }
        bpoSupplierInfo.setId(null);
        bpoSupplierInfo.setCreateTime(null);
        bpoSupplierInfo.setUpdateTime(null);
        bpoSupplierInfoMapper.insertSelective(bpoSupplierInfo);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result updateSupplier(ProjectSupplierParam projectSupplierParam) {
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();
        SysCompany companyByUserId = sysCompanyMapper.findCompanyByUserId(userId);
        Integer partyBId = companyByUserId.getId();

        Integer projectId = projectSupplierParam.getProjectId();
        List<FirstLevelSupplierParam> firstLevelSupplierParamList = projectSupplierParam.getFirstLevelSupplierParamList();
        for (FirstLevelSupplierParam firstLevelSupplierParam : firstLevelSupplierParamList) {
            Integer firstLevelSupplierId = firstLevelSupplierParam.getFirstLevelSupplierId();
            BpoSupplierInfo bpoSupplierInfo = new BpoSupplierInfo();
            bpoSupplierInfo.setId(firstLevelSupplierParam.getId());
            bpoSupplierInfo.setContractId(firstLevelSupplierParam.getPartyBToSupplierContractId());
            bpoSupplierInfo.setOrgId(firstLevelSupplierId);
            bpoSupplierInfo.setParentId(partyBId);
            bpoSupplierInfo.setProjectId(projectId);
            bpoSupplierInfoMapper.updateByPrimaryKeySelective(bpoSupplierInfo);
            List<SecondLevelSupplierParam> secondLevelSupplierParamList = firstLevelSupplierParam.getSecondLevelSupplierParamList();
            if (secondLevelSupplierParamList != null && !secondLevelSupplierParamList.isEmpty()) {
                for (SecondLevelSupplierParam secondLevelSupplierParam : secondLevelSupplierParamList) {
                    Integer secondLevelSupplierId = secondLevelSupplierParam.getSecondLevelSupplierId();
                    bpoSupplierInfo = new BpoSupplierInfo();
                    bpoSupplierInfo.setId(secondLevelSupplierParam.getId());
                    bpoSupplierInfo.setContractId(secondLevelSupplierParam.getSupplierToSupplierContractId());
                    bpoSupplierInfo.setOrgId(secondLevelSupplierId);
                    bpoSupplierInfo.setParentId(firstLevelSupplierId);
                    bpoSupplierInfo.setProjectId(projectId);
                    bpoSupplierInfoMapper.updateByPrimaryKeySelective(bpoSupplierInfo);
                }
            }
        }
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result addCreatedProjectConfig(CreatedProjectConfigParam configParam) {
        Result result = checkCreatedProjectConfigParam(configParam, true);
        if (result != null) {
            return result;
        }
        Byte type = configParam.getType();
        switch (type) {
            case SYS_PROCESS:
                BpoProcess bpoProcess = configParam.getBpoProcess();
                processMapper.insertSelective(bpoProcess);
                break;
            case SYS_MODULE:
                BpoWorkModel bpoWorkModel = configParam.getBpoWorkModel();
                workModelMapper.insertSelective(bpoWorkModel);
                break;
            case SYS_GROUP:
                BpoWorkGroup bpoWorkGroup = configParam.getBpoWorkGroup();
                workGroupMapper.insertSelective(bpoWorkGroup);
                break;
            default:
                return Result.requestParamError("type is invalid");
        }
        return Result.success();
    }

    private Result checkCreatedProjectConfigParam(CreatedProjectConfigParam configParam, boolean checkNameAndCodeExist) {
        Byte type = configParam.getType();
        if (type == null) {
            return Result.requestParamError("type is null");
        }
        String name;
        String code;
        String typeString;
        switch (type) {
            case SYS_GROUP:
                typeString = "group";
                BpoWorkGroup bpoWorkGroup = configParam.getBpoWorkGroup();
                name = bpoWorkGroup.getGroupName();
                code = bpoWorkGroup.getGroupCode();
                //已创建项目的配置和系统相关不在关联系统设置中的工序工作模块
//                Integer groupSysWorkModuleRelId = bpoWorkGroup.getSysWorkModuleRelId();
//                if (groupSysWorkModuleRelId == null) {
//                    return Result.requestParamError("groupSysWorkModuleRelId is null");
//                }
                Integer workModelId = bpoWorkGroup.getWorkModelId();
                if (workModelId == null) {
                    return Result.requestParamError("workModelId is null");
                }
                String groupCode = bpoWorkGroup.getGroupCode();
                if (StringUtils.isEmpty(groupCode)) {
                    return Result.requestParamError("groupCode is null");
                }
                String groupName = bpoWorkGroup.getGroupName();
                if (StringUtils.isEmpty(groupName)) {
                    return Result.requestParamError("groupName is null");
                }

                break;
            case SYS_MODULE:
                typeString = "model";
                BpoWorkModel bpoWorkModel = configParam.getBpoWorkModel();
                name = bpoWorkModel.getModelName();
                code = bpoWorkModel.getModuleCode();
                //已创建项目的配置和系统相关不在关联系统设置中的工序工作模块
//                Integer modelSysWorkModuleRelId = bpoWorkModel.getSysWorkModuleRelId();
//                if (modelSysWorkModuleRelId == null) {
//                    return Result.requestParamError("modelSysWorkModuleRelId is null");
//                }
                Integer projectId = bpoWorkModel.getProjectId();
                if (projectId == null) {
                    return Result.requestParamError("projectId is null");
                }
                String modelName = bpoWorkModel.getModelName();
                if (StringUtils.isEmpty(modelName)) {
                    return Result.requestParamError("modelName is null");
                }
                String moduleCode = bpoWorkModel.getModuleCode();
                if (StringUtils.isEmpty(moduleCode)) {
                    return Result.requestParamError("moduleCode is null");
                }
                break;
            case SYS_PROCESS:
                typeString = "process";
                BpoProcess bpoProcess = configParam.getBpoProcess();
                name = bpoProcess.getProcessName();
                code = bpoProcess.getProcessCode();
                //已创建项目的配置和系统相关不在关联系统设置中的工序工作模块
//                Integer processSysWorkModuleRelId = bpoProcess.getSysWorkModuleRelId();
//                if (processSysWorkModuleRelId == null) {
//                    return Result.requestParamError("processSysWorkModuleRelId is null");
//                }
                Integer workGroupId = bpoProcess.getWorkGroupId();
                if (workGroupId == null) {
                    return Result.requestParamError("workGroupId is null");
                }
                String processName = bpoProcess.getProcessName();
                if (StringUtils.isEmpty(processName)) {
                    return Result.requestParamError("processName is null");
                }
                String processCode = bpoProcess.getProcessCode();
                if (StringUtils.isEmpty(processCode)) {
                    return Result.requestParamError("processCode is null");
                }
                break;
            default:
                return Result.requestParamError("type is invalid");
        }
        if (checkNameAndCodeExist) {
            boolean codeExist = isCodeExist(code);
            if (codeExist) {
                return Result.requestParamError(typeString + " code is invalid");
            }
            boolean nameExist = isNameExist(name);
            if (nameExist) {
                return Result.requestParamError(typeString + " name is invalid");
            }
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result updateCreatedProjectConfig(CreatedProjectConfigParam configParam) {
        Result result = checkCreatedProjectConfigParam(configParam, false);
        if (result != null) {
            return result;
        }
        Byte type = configParam.getType();
        switch (type) {
            case SYS_PROCESS:
                BpoProcess bpoProcess = configParam.getBpoProcess();
                Integer processId = bpoProcess.getId();
                if (processId == null) {
                    return Result.requestParamError("processId is null");
                }
                processMapper.updateByPrimaryKeySelective(bpoProcess);
                break;
            case SYS_MODULE:
                BpoWorkModel bpoWorkModel = configParam.getBpoWorkModel();
                Integer modelId = bpoWorkModel.getId();
                if (modelId == null) {
                    return Result.requestParamError("modelId is null");
                }
                workModelMapper.updateByPrimaryKeySelective(bpoWorkModel);
                break;
            case SYS_GROUP:
                BpoWorkGroup bpoWorkGroup = configParam.getBpoWorkGroup();
                Integer groupId = bpoWorkGroup.getId();
                if (groupId == null) {
                    return Result.requestParamError("groupId is null");
                }
                workGroupMapper.updateByPrimaryKeySelective(bpoWorkGroup);
                break;
            default:
                return Result.requestParamError("type is invalid");
        }
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result deleteCreatedProjectConfig(CreatedProjectConfigParam configParam) {
        Byte type = configParam.getType();
        switch (type) {
            case SYS_MODULE:
                BpoWorkModel bpoWorkModel = configParam.getBpoWorkModel();
                Integer modelId = bpoWorkModel.getId();
                if (modelId == null) {
                    return Result.requestParamError("modelId is null");
                }
                List<BpoWorkGroup> workGroups = workGroupMapper.findByModelId(modelId);
                for (BpoWorkGroup workGroup : workGroups) {
                    List<BpoProcess> bpoProcesses = processMapper.findByGroupId(workGroup.getId());
                    for (BpoProcess process : bpoProcesses) {
                        process.setStatus(DELETE_STATUS);
                        processMapper.updateByPrimaryKeySelective(process);
                    }
                    workGroup.setStatus(DELETE_STATUS);
                    workGroupMapper.updateByPrimaryKeySelective(workGroup);
                }
                bpoWorkModel.setStatus(DELETE_STATUS);
                workModelMapper.updateByPrimaryKeySelective(bpoWorkModel);
                break;
            case SYS_GROUP:
                BpoWorkGroup bpoWorkGroup = configParam.getBpoWorkGroup();
                Integer groupId = bpoWorkGroup.getId();
                if (groupId == null) {
                    return Result.requestParamError("groupId is null");
                }
                List<BpoProcess> bpoProcesses = processMapper.findByGroupId(groupId);
                for (BpoProcess process : bpoProcesses) {
                    process.setStatus(DELETE_STATUS);
                    processMapper.updateByPrimaryKeySelective(process);
                }
                bpoWorkGroup.setStatus(DELETE_STATUS);
                workGroupMapper.updateByPrimaryKeySelective(bpoWorkGroup);
                break;
            case SYS_PROCESS:
                BpoProcess bpoProcess = configParam.getBpoProcess();
                Integer processId = bpoProcess.getId();
                if (processId == null) {
                    return Result.requestParamError("processId is null");
                }
                bpoProcess.setStatus(DELETE_STATUS);
                processMapper.updateByPrimaryKeySelective(bpoProcess);
                break;
            default:
                return Result.requestParamError("type is invalid");
        }
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result deleteSupplier(int[] ids) {
        BpoSupplierInfo bpoSupplierInfo;
        for (int id : ids) {
            BpoSupplierInfo info = bpoSupplierInfoMapper.selectByPrimaryKey(id);
            Integer parentId = info.getParentId();
            if (parentId == -1) {
                List<BpoSupplierInfo> list = bpoSupplierInfoMapper.findByParentId(id);
                for (BpoSupplierInfo supplierInfo : list) {
                    bpoSupplierInfo = new BpoSupplierInfo();
                    bpoSupplierInfo.setId(supplierInfo.getId());
                    bpoSupplierInfo.setStatus((byte) DELETE_STATUS);
                    bpoSupplierInfoMapper.updateByPrimaryKeySelective(bpoSupplierInfo);
                }
            }
            bpoSupplierInfo = new BpoSupplierInfo();
            bpoSupplierInfo.setId(id);
            bpoSupplierInfo.setStatus((byte) DELETE_STATUS);
            bpoSupplierInfoMapper.updateByPrimaryKeySelective(bpoSupplierInfo);
        }
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result updateFeeRule(List<FeeRuleParam> updateProductPriceParamList) {
        for (FeeRuleParam feeRuleParam : updateProductPriceParamList) {
            Integer ruleId = feeRuleParam.getRuleId();
            if (ruleId == null) {
                return Result.requestParamError("ruleId is null");
            }
            Integer computationalType = feeRuleParam.getComputationalType();
            if (computationalType == null) {
                return Result.requestParamError("computationalType is null");
            }
            String unitPrice = feeRuleParam.getUnitPrice();
            if (StringUtils.isEmpty(unitPrice)) {
                return Result.requestParamError("unitPrice is null");
            }
            String ruleName = feeRuleParam.getRuleName();
            if (StringUtils.isEmpty(ruleName)) {
                return Result.requestParamError("ruleName is null");
            }
            Integer priorityLevel = feeRuleParam.getPriorityLevel();
            if (priorityLevel == null) {
                return Result.requestParamError("priorityLevel is null");
            }
            Date effectiveTime = feeRuleParam.getEffectiveTime();
            if (effectiveTime == null) {
                return Result.requestParamError("effectiveTime is null");
            }
            Date finishTime = feeRuleParam.getFinishTime();
            if (finishTime == null) {
                return Result.requestParamError("finishTime is null");
            }
            Integer type = feeRuleParam.getType();
            if (type == null) {
                return Result.requestParamError("type is null");
            }
            if (computationalType == BY_NUMBER) {
                BpoFeeRule bpoFeeRule = new BpoFeeRule();
                bpoFeeRule.setId(ruleId);
                bpoFeeRule.setEffectiveTime(effectiveTime);
                bpoFeeRule.setFinishTime(finishTime);
                bpoFeeRule.setRuleName(ruleName);
                bpoFeeRule.setType(type);
                bpoFeeRule.setPriorityLevel(priorityLevel);
                bpoFeeRuleMapper.updateByPrimaryKeySelective(bpoFeeRule);
            } else if (computationalType == BY_TIME) {
                BpoFeeRuleByTime bpoFeeRuleByTime = new BpoFeeRuleByTime();
                bpoFeeRuleByTime.setId(ruleId);
                bpoFeeRuleByTime.setType(type);
                bpoFeeRuleByTime.setUnitPrice(unitPrice);
                bpoFeeRuleByTime.setPriorityLevel(priorityLevel);
                bpoFeeRuleByTime.setEffectiveTime(effectiveTime);
                bpoFeeRuleByTime.setFinishTime(finishTime);
                bpoFeeRuleByTime.setRuleName(ruleName);
                bpoFeeRuleByTimeMapper.updateByPrimaryKeySelective(bpoFeeRuleByTime);
            } else {
                return Result.requestParamError("computationalType is invalid");
            }
        }

        return Result.success();
    }


}
