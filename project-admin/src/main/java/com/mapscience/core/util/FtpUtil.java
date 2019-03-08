package com.mapscience.core.util;

import com.mapscience.config.properties.FtpProperties;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.SocketException;
import java.net.URLEncoder;


@Service
public class FtpUtil {

    @Autowired
    FtpProperties ftpProperties;
    public  void testFtp1(String path){

        //创建客户端对象
        FTPClient ftp = new FTPClient();
        InputStream local = null;
        try {
            //连接ftp服务器
            ftp.connect(ftpProperties.getHostname(), ftpProperties.getPort());
            //登录
            ftp.login(ftpProperties.getUsername(), ftpProperties.getPassword());
            //设置上传路径
            //检查上传路径是否存在 如果不存在返回false
            boolean flag = ftp.changeWorkingDirectory(path);
            if(!flag){
                //创建上传的路径  该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
                ftp.makeDirectory(path);
            }
            //指定上传路径
            ftp.changeWorkingDirectory(path);
            //指定上传文件的类型  二进制文件
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //读取本地文件
            File file = new File("E:/files/20181022110237977_secondUpload.pdf");
            local = new FileInputStream(file);
            //第一个参数是文件名
            ftp.storeFile(file.getName(), local);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭文件流
                local.close();
                //退出
                ftp.logout();
                //断开连接
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 上传文件到ftp服务器
     * @param fileName
     * @param is
     */
    public Boolean uploadFile(String fileName, InputStream is,String path){
        FTPClient ftp = new FTPClient();
        Boolean success = true;
        try {
            //连接ftp服务器
            ftp.connect(ftpProperties.getHostname(), ftpProperties.getPort());
            //登录
            ftp.login(ftpProperties.getUsername(), ftpProperties.getPassword());
            //设置上传路径
            //检查上传路径是否存在 如果不存在返回false
            boolean flag = ftp.changeWorkingDirectory(path);
            if(!flag){
                //创建上传的路径  该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
                ftp.makeDirectory(path);
            }
            //指定上传文件的类型  二进制文件
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            fileName=new String(fileName.getBytes("GBK"),"iso-8859-1");
            //第一个参数是文件名
            ftp.storeFile(fileName, is);
        } catch (SocketException e) {
            success = false;
            e.printStackTrace();
        } catch (IOException e) {
            success = false;
            e.printStackTrace();
        } finally {
            try {
                //关闭文件流
                is.close();
                //退出
                ftp.logout();
                //断开连接
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    /**
     * 下载ftp服务器上的文件
     * @param response
     * @param filePath
     * @param fileName
     * @return
     */
    public  boolean downFile(HttpServletResponse response, String filePath, String fileName,String path) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        //处理文件名
        String realname = filePath.substring(filePath.lastIndexOf("/")+1);
        try {

            int reply;
            //连接ftp服务器
            ftp.connect(ftpProperties.getHostname(), ftpProperties.getPort());
            //登录
            ftp.login(ftpProperties.getUsername(), ftpProperties.getPassword());
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(path);//转移到FTP服务器目录
            ftp.enterLocalPassiveMode();
            FTPFile[] fs = ftp.listFiles();
            for(FTPFile ff:fs){
                byte[] bytes=ff.getName().getBytes("iso-8859-1");
                ff.setName(new String(bytes,"GBK"));
                if(ff.getName().equals(realname)){
                    if(fileName != null && !"".equals(fileName)){
                        realname = fileName;
                    }
                    //设置响应头，控制浏览器下载该文件
                    response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
                    OutputStream out = response.getOutputStream();
                    String localFileName = new String(realname.getBytes("GBK"), "ISO-8859-1");
                    ftp.retrieveFile(localFileName, out);
                    out.close();
                }
            }
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }


    /**
     * 下载ftp服务器上的文件到本地
     * @param filePath
     * @param fileName
     * @return
     */
    public  boolean downFileToLocal(String filePath, String fileName,String path) {
        boolean success = false;
        FTPClient ftp = new FTPClient();

        //处理文件名
        String realname = filePath.substring(filePath.lastIndexOf("/")+1);
        try {

            int reply;
            //连接ftp服务器
            ftp.connect(ftpProperties.getHostname(), ftpProperties.getPort());
            //登录
            ftp.login(ftpProperties.getUsername(), ftpProperties.getPassword());
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(path);//转移到FTP服务器目录
            ftp.enterLocalPassiveMode();
            FTPFile[] fs = ftp.listFiles();
            String localPath="E:/files";

            for(FTPFile ff:fs){
                if(ff.getName().equals(realname)){

                    if(fileName != null && !"".equals(fileName)){
                        realname = fileName;
                    }
                    //设置响应头，控制浏览器下载该文件
                    File entryDir = new File(localPath);
                    //如果文件夹路径不存在，则创建文件夹
                    if (!entryDir.exists() || !entryDir.isDirectory()) {
                        entryDir.mkdirs();
                    }
                    File locaFile= new File(localPath + "/"+ realname);
                    //判断文件是否存在，存在则返回
                    if(locaFile.exists()){
                        return false;
                    }
                    OutputStream out = new FileOutputStream(locaFile);
                    ftp.retrieveFile(realname, out);
                    out.flush();
                    out.close();
                }
            }
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    /**
     * 从ftp服务器读取文件，输入流
     * @param fileName
     * @return
     */
    public  InputStream getInputStreamFromFile(String fileName,String path){
        FTPClient ftp = new FTPClient();

        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);

        try {
            int reply;
            //连接ftp服务器
            ftp.connect(ftpProperties.getHostname(), ftpProperties.getPort());
            //登录
            ftp.login(ftpProperties.getUsername(), ftpProperties.getPassword());
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return null;
            }
            ftp.changeWorkingDirectory(path);//转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();

            for(FTPFile ff:fs){
                if(ff.getName().equals(fileName)){
                    return ftp.retrieveFileStream(fileName);
                }
            }
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return null;
    }

    /**
     * 判断ftp服务器是否存在文件
     * @param fileName
     * @return
     */
    public boolean isExist(String fileName,String path){
        FTPClient ftp = new FTPClient();
        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        try {
            int reply;
            //连接ftp服务器
            ftp.connect(ftpProperties.getHostname(), ftpProperties.getPort());
            //登录
            ftp.login(ftpProperties.getUsername(), ftpProperties.getPassword());
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return false;
            }
            ftp.changeWorkingDirectory(path);//转移到FTP服务器目录
            ftp.enterLocalPassiveMode();
            FTPFile[] fs = ftp.listFiles();
            for(FTPFile ff:fs){
                if(ff.getName().equals(fileName)){
                    return true;
                }
            }
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return false;
    }
}
