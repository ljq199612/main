package com.rz.iot.bpo.common.utils;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.rz.iot.bpo.framework.web.entity.ZipModel;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 描述 : FastDFS操作文件工具类
 * 作者 : Rycony
 * 创建时间 : 2020/6/22 00:27
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Component
public class FastDFSClientWrapper {
    @Value("${fdfs.fdfs-nginx-server}")
    private String fdfsNginxServer;
    @Autowired
    private FastFileStorageClient storageClient;

    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
        return getResAccessUrl(storePath);
    }

    public byte[] downloadFile(String filePath) {
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        String groupName = "group1";
        String path = filePath.substring(filePath.indexOf(groupName) + groupName.length() + 1);
//        String groupName = filePath.substring(0, filePath.indexOf("/"));
//        String path = filePath.substring(filePath.indexOf("/") + 1, filePath.length());
        byte[] bytes = storageClient.downloadFile(groupName, path, downloadByteArray);
        return bytes;
    }

    // 封装文件完整URL地址
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = fdfsNginxServer + "/" + storePath.getFullPath();
        return fileUrl;
    }
    /**
     * 删除文件
     * @param fileUrl 文件访问地址
     * @return
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            e.getMessage();
        }
    }





    /**
     * 压缩文件列表中的文件
     *
     * @param files
     * @param outputStream
     * @throws IOException
     */
    public void zipFile(List<ZipModel> files, ZipOutputStream outputStream) throws IOException {
        try {
            int size = files.size();
            //压缩列表中的文件
            for (int i = 0; i < size; i++) {
                ZipModel zipModel = files.get(i);
                try {
                    zipFile(zipModel, outputStream);
                } catch (Exception e) {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件写入到zip文件中
     *
     * @param zipModel
     * @param outputstream
     * @throws IOException
     */
    public void zipFile(ZipModel zipModel, ZipOutputStream outputstream) {
        try {
            outputstream.setMethod(ZipOutputStream.DEFLATED);
            InputStream inputStream = new ByteArrayInputStream(zipModel.getData());
            inputStream.read(zipModel.getData());
            outputstream.putNextEntry(new ZipEntry(zipModel.getFileName()));
            outputstream.write(zipModel.getData());
            inputStream.close();
            outputstream.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 下载打包的文件
     *
     * @param file
     * @param response
     */
    public void downloadZip(File file, HttpServletResponse response) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            // 以流的形式下载文件。
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();

            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("utf-8"),"ISO8859-1"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            file.delete();  //将生成的服务器端文件删除
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 获得bean,通过ApplicationContext获取
     *
     * @return
     */
    public static Object getBean(String className) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ServletContext sc = request.getSession().getServletContext();
        ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
        Class c;
        try {
            c = Class.forName(className);
            return ac.getBean(c);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
