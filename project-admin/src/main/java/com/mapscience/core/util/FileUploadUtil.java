package com.mapscience.core.util;

import com.mapscience.modular.other.model.Picture;
import com.mapscience.modular.other.model.Video;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
@Component
public class FileUploadUtil {
    @Autowired
    private FtpUtil ftpUtil;
/*    *//**
     * 多文件上传
     * @return
     *//*
    public Map<String, Object> multiFileUpload(HttpServletRequest request,String savePath) {
        List<Picture> pictures = new ArrayList<Picture>();
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("保存文件路径为：" + savePath);
        //消息提示
        String message = "";
        String value = "";
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            factory.setSizeThreshold(1024 * 100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
            //设置上传时生成的临时文件的保存目录
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(request)) {
                //按照传统方式获取数据
                System.out.println("上传内容不是有效的multipart/form-data类型.");
                return map;
            }
            //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
            upload.setFileSizeMax(1024 * 1024);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，当前设置为10MB
            upload.setSizeMax(1024 * 1024 * 10);
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                    //获取普通文本框的name属性值
                    String fieldName = item.getFieldName();
                    //获取普通文本框的value值,解决普通输入项的数据的中文乱码问题
                    value = item.getString("UTF-8");
                    map.put(fieldName, value);

                } else {  //如果fileitem中封装的是上传文件
                    //得到上传的文件名，假设当前获取的文件名带有路径（注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt）
                    String fileNameUrl = item.getName();
                    if (fileNameUrl == null || "".equals(fileNameUrl.trim())) {
                        continue;
                    }
                    fileNameUrl = fileNameUrl.replaceAll("docx", "doc");
                    Long fileSize=item.getSize();

                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    String filenNameAndSuf = fileNameUrl.substring(fileNameUrl.lastIndexOf("\\") + 1);
                    //截取文件后缀
                    String fileNameSuf = filenNameAndSuf.substring(filenNameAndSuf.lastIndexOf(".") + 1);
                    //初始化日期格式
                    SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                    //以当前日期作为上传文件保存的名称
                    String newFileName = sd.format(new Date()) + "." + fileNameSuf;
                    System.out.println("新命名后的文件名为:" + newFileName);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //删除处理文件上传时生成的临时文件
                    item.delete();

                    //上传文件到ftp服务器
                    //FtpUtil.uploadFile(newFileName, in);

                    message = "success";
                    String path = savePath + "" + newFileName;

                    BigDecimal bigDecimal=new BigDecimal(fileSize);
                    Picture picture = new Picture();
                    picture.setPicName(filenNameAndSuf);
                    picture.setPicPath(path);
                    picture.setPicSize(bigDecimal);
                    picture.setPicNewname(newFileName);
                    picture.setCreateTime(new Date());
                    picture.setPicSuffix(fileNameSuf);
                    pictures.add(picture);
                }
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            message = "oneMoreBig"; //单个文件的大小超出限制
            e.printStackTrace();
        } catch (FileUploadBase.SizeLimitExceededException e) {
            message = "allMoreBig";  //上传文件的总的大小超出限制的最大值
            e.printStackTrace();
        } catch (Exception e) {
            message = "defeated"; //失败
            e.printStackTrace();
        }
        map.put("pictures", pictures);
        map.put("message", message);
        return map;
    }*/
    //单文件上传
    public Map<String,Object> fileUpload(MultipartFile file, String path){
        Map<String,Object> map=new HashMap<>();
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //获取文件名
        String fileName=file.getOriginalFilename();
        //以当前日期作加下划线加旧的文件名作为上传文件保存的名称
        String newFileName = sd.format(new Date())+"_"+fileName;
        //截取文件后缀
        String filenNameAndSuf = newFileName.substring(newFileName.lastIndexOf("\\") + 1);
        String suffix = filenNameAndSuf.substring(filenNameAndSuf.lastIndexOf(".") + 1);
        //获取文件大小
        Long fileSize=file.getSize();
        BigDecimal decimalSize=new BigDecimal(fileSize);
        //创建视频图片对象
        Picture picture=new Picture();
        Video video=new Video();
        String fileType=path.replace("/","");
        if(fileType.equals("video")){
            video.setVidName(fileName);
            video.setVidNewname(newFileName);
            video.setVidPath(path+newFileName);
            video.setVidSize(decimalSize);
            video.setCreateTime(new Date());
            video.setVidSuffix(suffix);
            map.put("video",video);

        }else if(fileType.equals("picture")){
            picture.setPicName(fileName);
            picture.setPicNewname(newFileName);
            picture.setPicPath(path+newFileName);
            picture.setPicSize(decimalSize);
            picture.setCreateTime(new Date());
            picture.setPicSuffix(suffix);
            map.put("picture",picture);
        }
        try {
            if(ftpUtil.uploadFile(newFileName,file.getInputStream(),path)){
                map.put("msg","success");
            }
        } catch (IOException e) {
            map.put("msg","false");
            return map;
        }
        return map;
    }
}

