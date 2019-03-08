package com.mapscience.modular.code.controller;

import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.util.FileUploadUtil;
import com.mapscience.core.util.FtpUtil;
import com.mapscience.modular.other.model.Picture;
import com.mapscience.modular.other.service.IPictureService;
import com.mapscience.modular.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "file")
public class FileController extends BaseController {
    @Autowired
    private FileUploadUtil fileUploadUtil;
    @Autowired
    private FtpUtil ftpUtil;
    @Autowired
    private IPictureService iPictureService;

    @ResponseBody
    @RequestMapping(value = "fileUpload")
    public ResponseVal fileUpload (MultipartFile file,Integer type) {
        /*String path="/picture/";
        User user=(User)getSession().getAttribute("userInfo");
        Map<String,Object> map=fileUploadUtil.fileUpload(file, path);
        if(map.get("msg").equals("success")){
            Picture picture=(Picture)map.get("picture");
            picture.setUploader(user.getName());
            picture.setPicType(type);
            Boolean b=iPictureService.insert(picture);
            if(b){
                return super.responseBody(ProjectStatusEnum.SUCCESS,picture.getId());
            }else{
                return super.responseBody(ProjectStatusEnum.ERROR);
            }
        }else{
            return super.responseBody(ProjectStatusEnum.ERROR);
        }*/
        return null;
    }
    @RequestMapping(value = "download")
    public void download(HttpServletResponse response){
        String filename="20181229092844270_四凌碑砂石视频监控.png";
        String filepath="/picture/20181229092844270_四凌碑砂石视频监控.png";
        ftpUtil.downFile(response,filepath,filename,"/picture/");
    }
}
