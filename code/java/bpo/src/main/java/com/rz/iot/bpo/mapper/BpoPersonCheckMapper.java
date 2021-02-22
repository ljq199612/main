package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.param.bpoPerson.BpoPersonCheckParam;
import com.rz.iot.bpo.model.show.bpoPerson.BpoBasePerson;
import com.rz.iot.bpo.model.show.bpoPerson.BpoPersonCheckShow;
import com.rz.iot.bpo.model.show.bpoPerson.BpoWorkPerson;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BpoPersonCheckMapper {
    List<BpoPersonCheckShow> findAll(BpoPersonCheckParam personCheckParam);

    BpoBasePerson getBaseInfo(Integer personId);

    void updateBaseProp(@Param("base") BpoBasePerson bpoBasePerson,@Param("prop") BpoWorkPerson bpoWorkPerson);

    // 根据人员id修改人员账户信息
    void updatePersonAccount(@Param("personId") Integer personId,@Param("contractId") Integer contractId, @Param("accountId") Integer accountId, @Param("wageId") Integer wageId);

    Map<String,Object> selectContractUrl(Integer personId);

    List<Map<String,Object>> findAllNation();

    // 人员绑定用户
    void personBindUser(@Param("personId") Integer personId,@Param("userId") Integer userId);
}
