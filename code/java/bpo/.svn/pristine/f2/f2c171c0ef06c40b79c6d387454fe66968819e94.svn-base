package com.rz.iot.bpo.common.utils;

import com.rz.iot.bpo.common.core.lang.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述 : 文件操作工具类
 * 作者 : Rycony
 * 创建时间 : 2020/6/21 23:56
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Component
public class PicUtils {
    @Resource
    private FastDFSClientWrapper fastDFSClientWrapper;
    /**
     * 上传多张文件
     * @param file
     * @return
     */
    public Map<String,String> upload(MultipartFile[] file) {
        // 遍历文件
        Map<String,String> map = new HashMap<>();
        for (int i =0;i < file.length;i++){
            try {
                String url = fastDFSClientWrapper.uploadFile(file[i]);
                if(file.length == 1){
                    map.put("url",url);
                }else {
                    map.put("url" + i,url);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return map;
    }

    /**
     * 删除文件
     * @param url 文件url
     */
    public void delete(String url){
        fastDFSClientWrapper.deleteFile(url);
    }

    /**
     * 下载文件
     * @param url
     * @param response
     */
    public void downloadFile(String url,HttpServletResponse response,String fileName){
        // 获取响应流
        try {
            OutputStream outputStream = response.getOutputStream();

            // 获取文件后缀
            // int index = url.lastIndexOf('.');
            // String suffix = url.substring(index);

            byte[] bts = fastDFSClientWrapper.downloadFile(url);
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            outputStream.write(bts);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 判断是否为图片
     * @param fileName 文件名
     * @return
     */
    public static boolean isPic(String fileName) {
        // 判断文件是否是图片
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
        switch (suffixName) {
            case "jpg":
                return true;
            case "JPG":
                return true;
            case "jpeg":
                return true;
            case "JPEG":
                return true;
            case "png":
                return true;
            case "PNG":
                return true;
            case "gif":
                return true;
            case "GIF":
                return true;
            case "bmp":
                return true;
            case "BMP":
                return true;
            default:
                return false;
        }
    }
}
