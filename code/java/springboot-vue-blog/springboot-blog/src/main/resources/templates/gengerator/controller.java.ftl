package ${package.Controller};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * ${entity}前端控制器
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName} ${table.entityPath}Service;

    /**
    *获取${entity}列表页面
    */
    @GetMapping("/${table.entityPath}s")
    public ResponseEntity index(@RequestParam(value = "pageNum", defaultValue = "0") Long pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Long pageSize) {
        Page<${entity}> page = new Page<>(pageNum,pageSize);
        return new ResponseEntity(${table.entityPath}Service.page(page,null), HttpStatus.OK);
    }

    /**
    *根据id查询${entity}
    */
    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id){
        return new ResponseEntity(${table.entityPath}Service.getById(id),HttpStatus.NO_CONTENT);
    }

    /**
    *保存${entity}
    */
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody ${entity} ${table.entityPath}){
        return new ResponseEntity(${table.entityPath}Service.save(${table.entityPath}),HttpStatus.CREATED);
    }

    /**
    *更新${entity}
    */
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody ${entity} ${table.entityPath}){
        return new ResponseEntity(${table.entityPath}Service.updateById(${table.entityPath}),HttpStatus.NO_CONTENT);
    }

    /**
    *根据id删除${entity}
    */
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id){
        ${table.entityPath}Service.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
</#if>
