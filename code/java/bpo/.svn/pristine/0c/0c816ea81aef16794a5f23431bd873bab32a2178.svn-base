package com.rz.iot.bpo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.controller.BpoEvaluationResultParam;
import com.rz.iot.bpo.framework.security.LoginUser;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.*;
import com.rz.iot.bpo.model.*;
import com.rz.iot.bpo.model.param.BpoEvaluationParam;
import com.rz.iot.bpo.model.param.BpoPerformanceEvaluationParam;
import com.rz.iot.bpo.model.param.BpoPerformanceLevelParam;
import com.rz.iot.bpo.model.param.EvaluationResultRecordParam;
import com.rz.iot.bpo.model.show.*;
import com.rz.iot.bpo.service.EvaluationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.rz.iot.bpo.common.constant.DictDataConstants.*;
import static com.rz.iot.bpo.common.constant.ResultConstants.REQUEST_SUCCESS;

@Service("evaluationService")
public class EvaluationServiceImpl implements EvaluationService {

    @Resource
    BpoPerformanceEvaluationMapper performanceEvaluationMapper;

    @Resource
    BpoPerformanceLevelMapper performanceLevelMapper;

    @Resource
    BpoEvaluationResultMapper evaluationResultMapper;

    @Resource
    SysCompanyMapper sysCompanyMapper;

    @Resource
    BpoProjectMapper projectMapper;

    @Resource
    BpoEvaluationResultRecordMapper bpoEvaluationResultRecordMapper;

    @Resource
    BpoEvaluationItemMapper bpoEvaluationItemMapper;

    @Resource
    BpoEvaluationContentMapper bpoEvaluationContentMapper;

    @Resource
    BpoEvaluationStandardMapper bpoEvaluationStandardMapper;

    @Override
    public Result<BpoPerformanceEvaluationShow> getDetail(int projectId) {
        if (projectId < 0) {
            return Result.requestParamError("projectId is invalid");
        }
        BpoPerformanceEvaluationShow performanceEvaluationShow = performanceEvaluationMapper.selectByProjectId(projectId);
        Result<BpoPerformanceEvaluationShow> result = new Result<>(REQUEST_SUCCESS);
        result.setData(performanceEvaluationShow);
        return result;
    }

    @Override
    public Result<BpoEvaluationShow> getEvaluationDetail(int projectId) {
        BpoEvaluationShow bpoEvaluationShow = bpoEvaluationItemMapper.findEvaluationShowByProjectId(projectId);
        Result<BpoEvaluationShow> result = new Result<>(REQUEST_SUCCESS);
        result.setData(bpoEvaluationShow);
        return result;
    }

    @Override
    public Result<BpoEvaluationResultRecordShow> getEvaluationDetailResultRecord(EvaluationResultRecordParam evaluationResultRecordParam) {
        BpoEvaluationResultRecordShow bpoEvaluationResultRecord = bpoEvaluationResultRecordMapper.findRecordByProjectIdAndTime(evaluationResultRecordParam);
        Result<BpoEvaluationResultRecordShow> result = new Result<>(REQUEST_SUCCESS);
        result.setData(bpoEvaluationResultRecord);
        return result;
    }

    @Override
    public Result<BpoPerformanceLevelShow> getLevel(int projectId) {
        if (projectId < 0) {
            return Result.requestParamError("projectId is invalid");
        }
        BpoPerformanceLevelShow bpoPerformanceLevelShow = performanceLevelMapper.selectByProjectId(projectId);
        Result<BpoPerformanceLevelShow> result = new Result<>(REQUEST_SUCCESS);
        result.setData(bpoPerformanceLevelShow);
        return result;
    }

    @Override
    public Result<BpoEvaluationResultShow> getResult(int projectId) {
        if (projectId < 0) {
            return Result.requestParamError("projectId is invalid");
        }
        BpoEvaluationResultShow bpoEvaluationResultShow = evaluationResultMapper.selectByProjectId(projectId);
        Result<BpoEvaluationResultShow> result = new Result<>(REQUEST_SUCCESS);
        result.setData(bpoEvaluationResultShow);
        return result;
    }

    @Override
    public Result<BpoEvaluationResultShow> getResultList(BpoEvaluationResultShow info, Page<BpoEvaluationResultShow> page) {
        PageHelper.startPage (page.getPageNum (),page.getPageSize ());

        List<BpoEvaluationResultShow> list = evaluationResultMapper.getProjectResultList(info);

        PageInfo<BpoEvaluationResultShow> pageInfo = new PageInfo<> (list);
        page.setList (list);
        page.setTotal (pageInfo.getTotal ());
        page.setPages (pageInfo.getPages ());
        return Result.success(page);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result updateLevel(BpoPerformanceLevelParam bpoPerformanceLevelParam) {
        Result result = handleUpdateLevelParam(bpoPerformanceLevelParam);
        if (result != null) {
            return result;
        }
        performanceLevelMapper.updateByPrimaryKeySelective(bpoPerformanceLevelParam);
        return Result.success();
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result updateResult(BpoEvaluationResultParam bpoEvaluationResultParam) {
        Result result = handleUpdateResultParam(bpoEvaluationResultParam);
        if (result != null) {
            return result;
        }
        evaluationResultMapper.updateByPrimaryKeySelective(bpoEvaluationResultParam);
        return Result.success();
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result updateEvaluationStandards(BpoPerformanceEvaluationParam bpoPerformanceEvaluationParam) {
        Result result = handleUpdateEvaluationStandardsParam(bpoPerformanceEvaluationParam);
        if (result != null) {
            return result;
        }
        performanceEvaluationMapper.updateByPrimaryKeySelective(bpoPerformanceEvaluationParam);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result updateEvaluationResultRecordScore(BpoEvaluationResultRecord bpoEvaluationResultRecord) {
        BpoEvaluationResultRecord resultRecord = new BpoEvaluationResultRecord();
        Integer score = bpoEvaluationResultRecord.getScore();
        Integer singleWeight = bpoEvaluationResultRecord.getSingleWeight();
        resultRecord.setId(bpoEvaluationResultRecord.getId());
        resultRecord.setScore(score);
        BigDecimal b1 = new BigDecimal(String.valueOf(singleWeight * score));
        BigDecimal b2 = new BigDecimal("0.01");
        BigDecimal multiply = b1.multiply(b2);
        resultRecord.setWeightedScore(multiply.toString());
        bpoEvaluationResultRecordMapper.updateByPrimaryKeySelective(resultRecord);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result updateEvaluationScoringResult(BpoEvaluationParam bpoEvaluationParam) {
        List<BpoEvaluationItem> bpoEvaluationItemList = bpoEvaluationParam.getBpoEvaluationItemList();
        String resultRecordDate = bpoEvaluationParam.getResultRecordDate();
        Integer resultId = null;
        for (BpoEvaluationItem bpoEvaluationItem : bpoEvaluationItemList) {
            String evaluationItemName = bpoEvaluationItem.getEvaluationItemName();
            Integer weight = bpoEvaluationItem.getWeight();
            Integer projectId = bpoEvaluationItem.getProjectId();
            Integer bpoEvaluationItemId = bpoEvaluationItem.getId();
            List<BpoEvaluationContent> bpoEvaluationContentList = bpoEvaluationItem.getBpoEvaluationContentList();
            for (BpoEvaluationContent bpoEvaluationContent : bpoEvaluationContentList) {
                Integer bpoEvaluationContentId = bpoEvaluationContent.getId();
                String evaluationContentName = bpoEvaluationContent.getEvaluationContentName();
                List<BpoEvaluationStandard> bpoEvaluationStandardList = bpoEvaluationContent.getBpoEvaluationStandardList();
                for (BpoEvaluationStandard bpoEvaluationStandard : bpoEvaluationStandardList) {
                    Integer singleWeight = bpoEvaluationStandard.getSingleWeight();
                    String evaluationStandardName = bpoEvaluationStandard.getEvaluationStandardName();
                    Integer score = bpoEvaluationStandard.getScore();
                    Integer scoreType = bpoEvaluationStandard.getScoreType();
                    Integer singleScore = bpoEvaluationStandard.getSingleScore();
                    Integer evaluationStandardId = bpoEvaluationStandard.getId();
                    BpoEvaluationResultRecord bpoEvaluationResultRecord = new BpoEvaluationResultRecord();
                    Integer recordId = bpoEvaluationStandard.getRecordId();
                    bpoEvaluationResultRecord.setId(recordId);
                    bpoEvaluationResultRecord.setProjectId(projectId);
                    bpoEvaluationResultRecord.setWeight(weight);
                    bpoEvaluationResultRecord.setSingleWeight(singleWeight);
                    bpoEvaluationResultRecord.setEvaluationContentId(bpoEvaluationContentId);
                    bpoEvaluationResultRecord.setEvaluationContentName(evaluationContentName);
                    bpoEvaluationResultRecord.setEvaluationItemId(bpoEvaluationItemId);
                    bpoEvaluationResultRecord.setEvaluationItemName(evaluationItemName);
                    bpoEvaluationResultRecord.setEvaluationStandardId(evaluationStandardId);
                    bpoEvaluationResultRecord.setEvaluationStandardName(evaluationStandardName);
                    bpoEvaluationResultRecord.setScore(score);
                    bpoEvaluationResultRecord.setScoreType(scoreType);
                    bpoEvaluationResultRecord.setSingleScore(singleScore);
                    bpoEvaluationResultRecord.setEvaluationTime(resultRecordDate);
                    BigDecimal b1 = new BigDecimal(String.valueOf(singleWeight * score));
                    BigDecimal b2 = new BigDecimal("0.01");
                    BigDecimal multiply = b1.multiply(b2);
                    bpoEvaluationResultRecord.setWeightedScore(multiply.toString());
                    if (resultId == null) {
                        resultId = bpoEvaluationResultRecordMapper.findResultIdByRecordId(recordId);
                    }
                    bpoEvaluationResultRecordMapper.updateByPrimaryKeySelective(bpoEvaluationResultRecord);
                }
            }
        }
        List<BpoEvaluationResultRecord> bpoEvaluationResultRecords = bpoEvaluationResultRecordMapper.findRecordByResultId(resultId);
        BigDecimal resultScore = new BigDecimal("0");
        for (BpoEvaluationResultRecord bpoEvaluationResultRecord : bpoEvaluationResultRecords) {
            resultScore = resultScore.add(new BigDecimal(bpoEvaluationResultRecord.getWeightedScore()));
        }
        BpoEvaluationResult bpoEvaluationResult = new BpoEvaluationResult();
        bpoEvaluationResult.setId(resultId);
        bpoEvaluationResult.setGrade(resultScore.toString());
        evaluationResultMapper.updateByPrimaryKeySelective(bpoEvaluationResult);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result updateEvaluationDetail(BpoEvaluationParam bpoEvaluationParam) {
        List<BpoEvaluationItem> bpoEvaluationItemList = bpoEvaluationParam.getBpoEvaluationItemList();
        for (BpoEvaluationItem bpoEvaluationItem : bpoEvaluationItemList) {
            bpoEvaluationItemMapper.updateByPrimaryKey(bpoEvaluationItem);
            List<BpoEvaluationContent> bpoEvaluationContentList = bpoEvaluationItem.getBpoEvaluationContentList();
            for (BpoEvaluationContent bpoEvaluationContent : bpoEvaluationContentList) {
                bpoEvaluationContentMapper.updateByPrimaryKey(bpoEvaluationContent);
                List<BpoEvaluationStandard> bpoEvaluationStandardList = bpoEvaluationContent.getBpoEvaluationStandardList();
                for (BpoEvaluationStandard bpoEvaluationStandard : bpoEvaluationStandardList) {
                    bpoEvaluationStandardMapper.updateByPrimaryKey(bpoEvaluationStandard);
                }
            }
        }
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result deleteEvaluationStandards(int id) {
        if (id < 0) {
            return Result.requestParamError("id is invalid");
        }
        BpoPerformanceEvaluation bpoPerformanceEvaluation = new BpoPerformanceEvaluation();
        bpoPerformanceEvaluation.setId(id);
        bpoPerformanceEvaluation.setStatus(DELETE_STATUS);
        int i = performanceEvaluationMapper.updateByPrimaryKeySelective(bpoPerformanceEvaluation);
        if (i < 1) {
            return Result.error("delete fail");
        }
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result deleteResult(int id) {
        if (id < 0) {
            return Result.requestParamError("id is invalid");
        }
        BpoEvaluationResult bpoEvaluationResult = new BpoEvaluationResult();
        bpoEvaluationResult.setId(id);
        bpoEvaluationResult.setStatus(DELETE_STATUS);
        int i = evaluationResultMapper.updateByPrimaryKeySelective(bpoEvaluationResult);
        if (i < 1) {
            return Result.error("delete fail");
        }
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result deleteLevel(int id) {
        if (id < 0) {
            return Result.requestParamError("id is invalid");
        }
        BpoPerformanceLevel bpoPerformanceLevel = new BpoPerformanceLevel();
        bpoPerformanceLevel.setId(id);
        bpoPerformanceLevel.setStatus(DELETE_STATUS);
        int i = performanceLevelMapper.updateByPrimaryKeySelective(bpoPerformanceLevel);
        if (i < 1) {
            return Result.error("delete fail");
        }
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result deleteEvaluationDetail(int id, int type) {
        switch (type) {
            case EVALUATION_ITEM:
                BpoEvaluationItem bpoEvaluationItem = new BpoEvaluationItem();
                bpoEvaluationItem.setId(id);
                bpoEvaluationItem.setStatus(DELETE_STATUS);
                bpoEvaluationItemMapper.updateByPrimaryKeySelective(bpoEvaluationItem);
                List<BpoEvaluationContent> byItemId = bpoEvaluationContentMapper.findByItemId(id);
                for (BpoEvaluationContent bpoEvaluationContent : byItemId) {
                    bpoEvaluationContent.setStatus(DELETE_STATUS);
                    bpoEvaluationContentMapper.updateByPrimaryKeySelective(bpoEvaluationContent);
                    Integer contentId = bpoEvaluationContent.getId();
                    List<BpoEvaluationStandard> byContentId = bpoEvaluationStandardMapper.findByContentId(contentId);
                    for (BpoEvaluationStandard bpoEvaluationStandard : byContentId) {
                        bpoEvaluationStandard.setStatus(DELETE_STATUS);
                        bpoEvaluationStandardMapper.updateByPrimaryKeySelective(bpoEvaluationStandard);
                    }
                }
                break;
            case EVALUATION_CONTENT:
                BpoEvaluationContent bpoEvaluationContent = new BpoEvaluationContent();
                bpoEvaluationContent.setId(id);
                bpoEvaluationContent.setStatus(DELETE_STATUS);
                bpoEvaluationContentMapper.updateByPrimaryKeySelective(bpoEvaluationContent);
                List<BpoEvaluationStandard> byContentId = bpoEvaluationStandardMapper.findByContentId(id);
                for (BpoEvaluationStandard bpoEvaluationStandard : byContentId) {
                    bpoEvaluationStandard.setStatus(DELETE_STATUS);
                    bpoEvaluationStandardMapper.updateByPrimaryKeySelective(bpoEvaluationStandard);
                }
                break;
            case EVALUATION_STANDARD:
                BpoEvaluationStandard bpoEvaluationStandard = new BpoEvaluationStandard();
                bpoEvaluationStandard.setId(id);
                bpoEvaluationStandard.setStatus(DELETE_STATUS);
                bpoEvaluationStandardMapper.updateByPrimaryKeySelective(bpoEvaluationStandard);
                break;
            default:
                return Result.requestParamError("unknown type");
        }
        return Result.success();
    }

    @Override
    public Result<List<BpoProjectEvaluationShow>> getCurrentPartyBAllProject() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Integer userId = loginUser.getUser().getUserId();
        SysCompany company = sysCompanyMapper.findCompanyByUserId(userId);
        Integer partyBId = company.getId();
        List<BpoProjectEvaluationShow> bpoProjectEvaluationShow = projectMapper.findPartyBProjectEvaluation(partyBId);
        Result<List<BpoProjectEvaluationShow>> result = new Result<>(REQUEST_SUCCESS);
        result.setData(bpoProjectEvaluationShow);
        return result;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result addResult(BpoEvaluationResultParam bpoEvaluationResultParam) {
        Result result = handleAddResultParam(bpoEvaluationResultParam);
        if (result != null) {
            return result;
        }
        evaluationResultMapper.insertSelective(bpoEvaluationResultParam);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result addEvaluationDetail(BpoEvaluationParam bpoEvaluationParam) {
        Integer projectId = bpoEvaluationParam.getProjectId();
        List<BpoEvaluationItem> bpoEvaluationItemList = bpoEvaluationParam.getBpoEvaluationItemList();
        for (BpoEvaluationItem bpoEvaluationItem : bpoEvaluationItemList) {
            bpoEvaluationItem.setProjectId(projectId);
            if (bpoEvaluationItem.getId() == null) {
                bpoEvaluationItemMapper.insertSelective(bpoEvaluationItem);
            }
            Integer bpoEvaluationItemId = bpoEvaluationItem.getId();
            List<BpoEvaluationContent> bpoEvaluationContentList = bpoEvaluationItem.getBpoEvaluationContentList();
            for (BpoEvaluationContent bpoEvaluationContent : bpoEvaluationContentList) {
                if (bpoEvaluationContent.getId() == null) {
                    bpoEvaluationContent.setEvaluationItemId(bpoEvaluationItemId);
                    bpoEvaluationContentMapper.insertSelective(bpoEvaluationContent);
                }
                Integer bpoEvaluationContentId = bpoEvaluationContent.getId();
                List<BpoEvaluationStandard> bpoEvaluationStandardList = bpoEvaluationContent.getBpoEvaluationStandardList();
                for (BpoEvaluationStandard bpoEvaluationStandard : bpoEvaluationStandardList) {
                    bpoEvaluationStandard.setEvaluationContentId(bpoEvaluationContentId);
                    bpoEvaluationStandardMapper.insertSelective(bpoEvaluationStandard);
                }
            }
        }
        return Result.success();
    }


    /**
     * 添加考核项目分数结果到数据库
     * 并计算出分数结果
     * @param bpoEvaluationParam
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result addEvaluationScoringResult(BpoEvaluationParam bpoEvaluationParam) {
        List<BpoEvaluationItem> bpoEvaluationItemList = bpoEvaluationParam.getBpoEvaluationItemList();
        String resultRecordDate = bpoEvaluationParam.getResultRecordDate();
        Integer projectId = null;
        List<BpoEvaluationResultRecord> bpoEvaluationResultRecords = new ArrayList<>();
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();
        BigDecimal resultScore = new BigDecimal("0");
        //生成考核结果
        for (BpoEvaluationItem bpoEvaluationItem : bpoEvaluationItemList) {
            String evaluationItemName = bpoEvaluationItem.getEvaluationItemName();
            Integer weight = bpoEvaluationItem.getWeight();
            projectId = bpoEvaluationItem.getProjectId();
            Integer bpoEvaluationItemId = bpoEvaluationItem.getId();
            List<BpoEvaluationContent> bpoEvaluationContentList = bpoEvaluationItem.getBpoEvaluationContentList();
            for (BpoEvaluationContent bpoEvaluationContent : bpoEvaluationContentList) {
                Integer bpoEvaluationContentId = bpoEvaluationContent.getId();
                String evaluationContentName = bpoEvaluationContent.getEvaluationContentName();
                List<BpoEvaluationStandard> bpoEvaluationStandardList = bpoEvaluationContent.getBpoEvaluationStandardList();
                for (BpoEvaluationStandard bpoEvaluationStandard : bpoEvaluationStandardList) {
                    Integer singleWeight = bpoEvaluationStandard.getSingleWeight();
                    String evaluationStandardName = bpoEvaluationStandard.getEvaluationStandardName();
                    Integer score = bpoEvaluationStandard.getScore();
                    Integer scoreType = bpoEvaluationStandard.getScoreType();
                    Integer singleScore = bpoEvaluationStandard.getSingleScore();
                    Integer evaluationStandardId = bpoEvaluationStandard.getId();
                    Integer upperLimit = bpoEvaluationStandard.getUpperLimit();
                    ////判断考核结果是否存在,存在就更新,不存在添加
                    BpoEvaluationResultRecord bpoEvaluationResultRecord =
                            bpoEvaluationResultRecordMapper.findRecord(bpoEvaluationItemId, evaluationStandardId, bpoEvaluationContentId,resultRecordDate);
                    boolean isAdd = false;
                    if (bpoEvaluationResultRecord == null) {
                        bpoEvaluationResultRecord = new BpoEvaluationResultRecord();
                        isAdd = true;
                    }
                    bpoEvaluationResultRecord.setProjectId(projectId);
                    bpoEvaluationResultRecord.setWeight(weight);
                    bpoEvaluationResultRecord.setSingleWeight(singleWeight);
                    bpoEvaluationResultRecord.setEvaluationContentId(bpoEvaluationContentId);
                    bpoEvaluationResultRecord.setEvaluationContentName(evaluationContentName);
                    bpoEvaluationResultRecord.setEvaluationItemId(bpoEvaluationItemId);
                    bpoEvaluationResultRecord.setEvaluationItemName(evaluationItemName);
                    bpoEvaluationResultRecord.setEvaluationStandardId(evaluationStandardId);
                    bpoEvaluationResultRecord.setEvaluationStandardName(evaluationStandardName);
                    bpoEvaluationResultRecord.setScore(score);
                    bpoEvaluationResultRecord.setScoreType(scoreType);
                    bpoEvaluationResultRecord.setSingleScore(singleScore);
                    bpoEvaluationResultRecord.setEvaluationTime(resultRecordDate);
                    bpoEvaluationResultRecord.setUpperLimit(upperLimit);
                    bpoEvaluationResultRecord.setUserId(userId);
                    BigDecimal b1 = new BigDecimal(String.valueOf(singleWeight * score));
                    BigDecimal b2 = new BigDecimal("0.01");
                    BigDecimal multiply = b1.multiply(b2);
                    bpoEvaluationResultRecord.setWeightedScore(multiply.toString());
                    resultScore = resultScore.add(multiply);

                    if (isAdd){
                        bpoEvaluationResultRecords.add(bpoEvaluationResultRecord);
                    }else {
                        bpoEvaluationResultRecordMapper.updateByPrimaryKeySelective(bpoEvaluationResultRecord);
                    }
                }
            }
        }
        //查询考核结果,判断更新和添加
        Integer resultId = bpoEvaluationResultRecordMapper.findResultIdBYProjectIdAndTime(projectId, resultRecordDate);
        if (resultId == null) {
            BpoEvaluationResult bpoEvaluationResult = new BpoEvaluationResult();
            bpoEvaluationResult.setProjectId(projectId);
            bpoEvaluationResult.setUserId(userId);
            bpoEvaluationResult.setGrade(resultScore.toString());
            evaluationResultMapper.insertSelective(bpoEvaluationResult);
            resultId = bpoEvaluationResult.getId();
        } else {
            List<BpoEvaluationResultRecord> recordByResultId = bpoEvaluationResultRecordMapper.findRecordByResultId(resultId);
            resultScore = new BigDecimal("0");
            for (BpoEvaluationResultRecord bpoEvaluationResultRecord : recordByResultId) {
                resultScore = resultScore.add(new BigDecimal(bpoEvaluationResultRecord.getWeightedScore()));
            }
            BpoEvaluationResult bpoEvaluationResult = new BpoEvaluationResult();
            bpoEvaluationResult.setId(resultId);
            bpoEvaluationResult.setProjectId(projectId);
            bpoEvaluationResult.setUserId(userId);
            bpoEvaluationResult.setGrade(resultScore.toString());
            evaluationResultMapper.updateByPrimaryKeySelective(bpoEvaluationResult);
        }
        //最后添加结果
        for (BpoEvaluationResultRecord bpoEvaluationResultRecord : bpoEvaluationResultRecords) {
//            bpoEvaluationResultRecordMapper.findRecord(evaluationItemId, evaluationStandardId, evaluationContentId);
            bpoEvaluationResultRecord.setResultId(resultId);
            bpoEvaluationResultRecordMapper.insertSelective(bpoEvaluationResultRecord);
        }
        return Result.success();
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result addLevel(List<BpoPerformanceLevelParam> bpoPerformanceLevelParamList) {
        for (BpoPerformanceLevelParam bpoPerformanceLevelParam : bpoPerformanceLevelParamList) {
            Result result = handelAddLevelParam(bpoPerformanceLevelParam);
            if (result != null) {
                return result;
            }
        }
        for (BpoPerformanceLevel bpoPerformanceLevelParam : bpoPerformanceLevelParamList) {
            performanceLevelMapper.insertSelective(bpoPerformanceLevelParam);
        }
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result addEvaluationStandards(BpoPerformanceEvaluationParam bpoPerformanceEvaluationParam) {
        Result result = handleAddEvaluationStandardsParam(bpoPerformanceEvaluationParam);
        if (result != null) {
            return result;
        }
        performanceEvaluationMapper.insertSelective(bpoPerformanceEvaluationParam);
        return Result.success();
    }


    private Result handleUpdateEvaluationStandardsParam(BpoPerformanceEvaluationParam bpoPerformanceEvaluationParam) {
        Integer id = bpoPerformanceEvaluationParam.getId();
        if (id == null) {
            return Result.requestParamError("id is null");
        }
        Integer projectId = bpoPerformanceEvaluationParam.getProjectId();
        if (projectId == null) {
            return Result.requestParamError("projectId is null");
        }
        String linkRate = bpoPerformanceEvaluationParam.getLinkRate();
        if (StringUtils.isEmpty(linkRate)) {
            return Result.requestParamError("linkRate is null");
        }
        String guaranteeRate = bpoPerformanceEvaluationParam.getGuaranteeRate();
        if (StringUtils.isEmpty(guaranteeRate)) {
            return Result.requestParamError("guaranteeRate is null");
        }
        return null;
    }

    private Result handleUpdateLevelParam(BpoPerformanceLevelParam bpoPerformanceLevelParam) {
        Integer id = bpoPerformanceLevelParam.getId();
        if (id == null) {
            return Result.requestParamError("id is null");
        }
        String performanceLevel = bpoPerformanceLevelParam.getPerformanceLevel();
        if (StringUtils.isEmpty(performanceLevel)) {
            return Result.requestParamError("performanceLevel is null");
        }
        Integer upperLimit = bpoPerformanceLevelParam.getUpperLimit();
        if (upperLimit == null) {
            return Result.requestParamError("upperLimit is null");
        }
        Integer lowerLimit = bpoPerformanceLevelParam.getLowerLimit();
        if (lowerLimit == null) {
            return Result.requestParamError("lowerLimit is null");
        }
        String feeExpendRateMinimum = bpoPerformanceLevelParam.getFeeExpendRateMinimum();
        if (StringUtils.isEmpty(feeExpendRateMinimum)) {
            return Result.requestParamError("feeExpendRateMinimum is null");
        }
        Integer increase = bpoPerformanceLevelParam.getIncrease();
        if (increase == null) {
            return Result.requestParamError("increase is null");
        }
        Integer levelProjectId = bpoPerformanceLevelParam.getProjectId();
        if (levelProjectId == null) {
            return Result.requestParamError("levels ProjectId is null");
        }
        return null;
    }

    private Result handleUpdateResultParam(BpoEvaluationResultParam bpoEvaluationResultParam) {
        Integer id = bpoEvaluationResultParam.getId();
        if (id == null) {
            return Result.requestParamError("id is null");
        }
        Integer projectId = bpoEvaluationResultParam.getProjectId();
        if (projectId == null) {
            return Result.requestParamError("projectId is null");
        }
        String grade = bpoEvaluationResultParam.getGrade();
        if (StringUtils.isEmpty(grade)) {
            return Result.requestParamError("grade is null");
        }
        Integer userId = bpoEvaluationResultParam.getUserId();
        if (userId == null) {
            return Result.requestParamError("userId is null");
        }
        Integer status = bpoEvaluationResultParam.getStatus();
        if (status == null) {
            return Result.requestParamError("status is null");
        }
        String evaluationTime = bpoEvaluationResultParam.getEvaluationTime();
        if (StringUtils.isEmpty(evaluationTime)) {
            return Result.requestParamError("evaluationTime is null");
        }
        return null;
    }

    private Result handleAddResultParam(BpoEvaluationResultParam bpoEvaluationResultParam) {
        bpoEvaluationResultParam.setId(null);
        Integer projectId = bpoEvaluationResultParam.getProjectId();
        if (projectId == null) {
            return Result.requestParamError("projectId is null");
        }
        String grade = bpoEvaluationResultParam.getGrade();
        if (StringUtils.isEmpty(grade)) {
            return Result.requestParamError("grade is null");
        }
        Integer userId = bpoEvaluationResultParam.getUserId();
        if (userId == null) {
            return Result.requestParamError("userId is null");
        }
        Integer status = bpoEvaluationResultParam.getStatus();
        if (status == null) {
            return Result.requestParamError("status is null");
        }
        String evaluationTime = bpoEvaluationResultParam.getEvaluationTime();
        if (StringUtils.isEmpty(evaluationTime)) {
            return Result.requestParamError("evaluationTime is null");
        }
        return null;
    }

    private Result handelAddLevelParam(BpoPerformanceLevelParam bpoPerformanceLevelParam) {
        bpoPerformanceLevelParam.setId(null);
        bpoPerformanceLevelParam.setSupplierInfoId(null);
        String performanceLevel = bpoPerformanceLevelParam.getPerformanceLevel();
        if (StringUtils.isEmpty(performanceLevel)) {
            return Result.requestParamError("performanceLevel is null");
        }
        Integer upperLimit = bpoPerformanceLevelParam.getUpperLimit();
        if (upperLimit == null) {
            return Result.requestParamError("upperLimit is null");
        }
        Integer lowerLimit = bpoPerformanceLevelParam.getLowerLimit();
        if (lowerLimit == null) {
            return Result.requestParamError("lowerLimit is null");
        }
        String feeExpendRateMinimum = bpoPerformanceLevelParam.getFeeExpendRateMinimum();
        if (StringUtils.isEmpty(feeExpendRateMinimum)) {
            return Result.requestParamError("feeExpendRateMinimum is null");
        }
        Integer increase = bpoPerformanceLevelParam.getIncrease();
        if (increase == null) {
            return Result.requestParamError("increase is null");
        }
        Integer levelProjectId = bpoPerformanceLevelParam.getProjectId();
        if (levelProjectId == null) {
            return Result.requestParamError("levels ProjectId is null");
        }
        return null;
    }

    private Result handleAddEvaluationStandardsParam(BpoPerformanceEvaluationParam bpoPerformanceEvaluationParam) {
        bpoPerformanceEvaluationParam.setId(null);
        Integer projectId = bpoPerformanceEvaluationParam.getProjectId();
        if (projectId == null) {
            return Result.requestParamError("projectId is null");
        }
        String linkRate = bpoPerformanceEvaluationParam.getLinkRate();
        if (StringUtils.isEmpty(linkRate)) {
            return Result.requestParamError("linkRate is null");
        }
        String guaranteeRate = bpoPerformanceEvaluationParam.getGuaranteeRate();
        if (StringUtils.isEmpty(guaranteeRate)) {
            return Result.requestParamError("guaranteeRate is null");
        }
        return null;
    }
}
