package com.rz.iot.bpo.model.param;

import com.rz.iot.bpo.model.BpoProcess;
import com.rz.iot.bpo.model.BpoSupplierInfo;
import com.rz.iot.bpo.model.BpoWorkGroup;
import com.rz.iot.bpo.model.BpoWorkModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 描述 : 项目配置参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class ProjectConfigParam {
    //项目id
    private Integer projectId;
    //中转场id
    @ApiModelProperty(value = "中转场id", example = "1")
    private Integer transferStationId;
    //中转场id
    @ApiModelProperty(value = "中转场名字", example = "这是一个中转场")
    private String transferStationName;

    //工作模块id
    @ApiModelProperty(value = "工作模块")
    private List<BpoWorkModelParam> workModels;

    public static void main(String[] args) {
        Class<BpoWorkGroup> bpoProjectClass = BpoWorkGroup.class;
        Field[] fields = bpoProjectClass.getDeclaredFields();
        String paramName = "bpoWorkGroup";
        //BpoWorkGroup bpoWorkGroup
        for (Field field : fields) {
            String name = field.getName();
            Class<?> type = field.getType();
            String s = name.substring(0,1).toUpperCase()+name.substring(1);
            switch (type.toString()) {
                case "class java.lang.Integer":
                    System.out.println("Integer " + name + " = "+paramName+".get" + s + "();\n" +
                            "        if (" + name + " == null){\n" +
                            "            return Result.requestParamError(\"" + name + " is null\");\n" +
                            "        }");
                    break;
                case "class java.lang.String":
                    System.out.println("String " + name + " = "+paramName+".get" + s + "();\n" +
                            "        if (StringUtils.isEmpty(" + name + ")){\n" +
                            "            return Result.requestParamError(\"" + name + " is null\");\n" +
                            "        }");
                    break;
                case "class java.util.Date":
                    System.out.println("Date " + name + " = "+paramName+".get" + s + "();\n" +
                            "        if (" + name + " == null){\n" +
                            "            return Result.requestParamError(\"" + name + " is null\");\n" +
                            "        }");

                    break;
                case "interface java.util.List":
                    System.out.println("List " + name + " = "+paramName+".get" + s + "();\n" +
                            "        if (" + name + " == null||"+ name +".isEmpty()){\n" +
                            "            return Result.requestParamError(\"" + name + " is null\");\n" +
                            "        }");

                    break;
            }
        }
    }
}
