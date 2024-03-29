package com.rz.iot.bpo.mina.controller;

import com.alibaba.fastjson.JSONObject;
import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.utils.StringUtils;
import com.rz.iot.bpo.framework.security.handle.LogoutSuccessHandlerImpl;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mina.model.MinaPerson;
import com.rz.iot.bpo.mina.model.param.MinaLoginParm;
import com.rz.iot.bpo.mina.service.MinaPersonCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @program: bpo-preview
 * @description: 个人中心
 * @author: YangShun
 * @create: 2020-07-22 17:26
 **/
@RestController
@RequestMapping("minaPersonCenter")
public class MinaPersonCenterController {

    @Autowired
    private MinaPersonCenterService personCenterService;

    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    /**
     * 工作经历
     * @return
     */
    @GetMapping("workExperience")
    public Result workExperience(){
        return personCenterService.getWorkExperience ();
    }

    /**
     * 联系驻场
     * @return
     */
    @GetMapping("contactResident")
    public Result contactResident(){
        return personCenterService.getLinkMode ();
    }

    /**
     * 更换密码
     * @return
     */
    @PostMapping("changePassword")
    public Result changePassword(MinaLoginParm parm){
        return personCenterService.changePassword (parm);
    }

    /**
     * 忘记密码
     * @return
     */
    @PostMapping("forgetPassword")
    public Result forgetPassword(){
        return null;
    }

    /**
     * 退出账号
     * @return
     */
    @GetMapping("logout")
    public Result retreat(){
        return null;
    }

    /**
     * 登录账号
     * @param code 微信登录传入code
     * @param param 用户输入的账号密码
     * @param type 1.手机号、账号、工号 2.微信登录
     * @return
     */
    @GetMapping("login")
    public Result login( String code, MinaLoginParm param, Integer type) throws IOException {
        StringBuffer url = new StringBuffer (DictDataConstants.APPURL);
        url.append ("appid="+ DictDataConstants.APPID);
        url.append ("&secret="+DictDataConstants.APPSECRET);
        url.append ("&js_code="+code);
        url.append ("&grant_type"+DictDataConstants.GRANT_TYPE);
        String result = sendGet (url.toString (), new HashMap<> ());
        JSONObject parse = JSONObject.parseObject (result);
        if(parse.get ("errcode")!=null){
            return Result.requestParamError (parse.get ("errmsg").toString ());
        }

        if (StringUtils.isAnyEmpty (
                param.getUserName (),
                param.getPassword ()
        )) {
            return personCenterService.login (parse.get ("openid").toString (), null);
        }
        return personCenterService.login (parse.get ("openid").toString (), param);

    }

    /**
     * 注册账号
     * @param person 姓名、身份证号、手机号、地址
     * @return
     */
    @PostMapping("signUp")
    public Result signUp(MinaPerson person){
        return personCenterService.signUp (person);
    }

    /**
     * 获取用户详细信息
     * @return
     */
    @GetMapping("getInfo")
    public Result getInfo(){
        return personCenterService.getInfo ();
    }

    private String sendGet(String url, Map<String, String> header) throws UnsupportedEncodingException, IOException {
        String result = "";
        BufferedReader in = null;
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection connection = realUrl.openConnection();
        //设置超时时间
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(15000);
        // 设置通用的请求属性
        if (header!=null) {
            Iterator<Map.Entry<String, String>> it =header.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, String> entry = it.next();
                System.out.println(entry.getKey()+":::"+entry.getValue());
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        // 建立实际的连接
        connection.connect();
        // 获取所有响应头字段
        Map<String, List<String>> map = connection.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : map.keySet()) {
            System.out.println(key + "--->" + map.get(key));
        }
        // 定义 BufferedReader输入流来读取URL的响应，设置utf8防止中文乱码
        in = new BufferedReader (new InputStreamReader (connection.getInputStream(), "utf-8"));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        if (in != null) {
            in.close();
        }
        return result;
    }

}
