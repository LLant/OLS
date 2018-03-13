package org.gdpu.ols.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.gdpu.ols.bean.TeacherCustom;
import org.gdpu.ols.common.ResponseBean;
import org.gdpu.ols.model.File;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.service.CoursewareService;
import org.gdpu.ols.service.FileService;
import org.gdpu.ols.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RequestMapping("/courseware")
public class FileController {

    private static final String ERROR_CODE="9999";
    private static final String SUCCESS_CODE="8888";
    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource
    private FileService fileService;

    @Resource
    private CoursewareService coursewareService;

    @PostMapping(value = "/update")
    @ResponseBody
    @Transactional
    public ResponseBean updateCourseware(@RequestParam(value = "photo", required = false) MultipartFile photo,
                                         @RequestParam(value = "video", required = false) MultipartFile video,
                                         @RequestParam(value = "attach", required = false) MultipartFile attach,
                                         @RequestParam(value = "courseName", required = true) String courseName,
                                         @RequestParam(value = "courseIntroduction", required = true) String courseIntroduction,
                                         @RequestParam(value = "courseTip", required = true) String courseTip,
                                         HttpSession session) {
        Student user = null;
        ResponseBean responseBean=new ResponseBean();
        if (session.getAttribute(session.getId()) != null) {
            user = (Student) session.getAttribute(session.getId());
        }else {
            responseBean.setResultCode(ERROR_CODE);
            responseBean.setResultMessage("请先登录用户");
            return responseBean;
        }
        String currentTime = String.valueOf(System.currentTimeMillis());
        String savePath="/images/" + user.getId() + "/" + currentTime + "/";
        int coursewareId=this.coursewareService.addSingleCourseware(courseName,courseIntroduction,courseTip,
                user.getId());
        try {
            if (!photo.isEmpty()) {
                this.saveFile(photo, savePath,user.getId(),coursewareId);
            }
            if (!video.isEmpty()) {
                this.saveFile(video, savePath,user.getId(),coursewareId);
            }
            if (!attach.isEmpty()) {
                this.saveFile(attach, savePath,user.getId(),coursewareId);
            }
        } catch (IOException e) {
            e.printStackTrace();
            responseBean.setResultCode(ERROR_CODE);
            responseBean.setResultMessage("文件上传错误");
        }
        responseBean.setResultCode(SUCCESS_CODE);
        responseBean.setResultMessage("文件上传成功");
        return responseBean;
    }

    private void saveFile(MultipartFile file, String path,int author,int courseware) throws IOException {
        String systemPath = ClassUtils.getDefaultClassLoader().getResource("").getPath()
                + "static";
        // 文件保存路径
        String savePath = systemPath.substring(1, systemPath.length())+path;
        java.io.File uploadPath = new java.io.File(savePath);
        if (!uploadPath.exists()) {
            uploadPath.mkdir();
        }
        java.io.File uploadPic = new java.io.File(uploadPath + "/" + file.getOriginalFilename());
        if (uploadPic.exists()) {
            uploadPic.delete();
        }
        // 转存文件
        file.transferTo(uploadPic);

        File file1=new File();
        file1.setAuthor(author);
        file1.setCourseware(courseware);
        file1.setStorageLocation(path);

        String[] names=file.getOriginalFilename().split("\\.");
        file1.setFileName(file.getOriginalFilename());
        file1.setFileType(names[1]);
        if("mp4".equals(names[1]) || "avi".equals(names[1])){
            file1.setMainFile(1);
        }else {
            file1.setMainFile(0);
        }
        file1.setPublishDate(new Date());
        this.fileService.save(file1);

    }
}
