package org.gdpu.ols.controller;

import org.gdpu.ols.bean.StudentCustom;
import org.gdpu.ols.bean.TeacherCustom;
import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.common.ResponseBean;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.model.Teacher;
import org.gdpu.ols.service.StudentService;
import org.gdpu.ols.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
@RequestMapping(value = "/OLS/info")
public class PersonInfoController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(PersonInfoController.class);
    private static final String SUCCESS_CODE="8888";
    private static final String ERROR_CODE="9999";

    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;

    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    public String apply(){
        return "apply";
    }

    @PostMapping(value = "/applyInfo")
    public @ResponseBody ResponseBean applyTeacher(HttpSession session,@RequestBody TeacherCustom teacherCustom){

        ResponseBean responseBean=new ResponseBean();
        Teacher teacher=new Teacher();
        if (session.getAttribute(session.getId()) != null){
            Student student= (Student) session.getAttribute(session.getId());
            teacher.setTeacherName(student.getStudentName());
            teacher.setTeacherSex(student.getStudentSex());
            teacher.setTeacherPassword(student.getStudentPassword());
            teacher.setTeacherEmail(student.getStudentEmail());
        }else{
            responseBean.setResultCode(ERROR_CODE);
            responseBean.setResultMessage("登录过期");

            return responseBean;
        }

        teacher.setRealName(teacherCustom.getRealName());
        teacher.setUniversity(teacherCustom.getUniversity());
        teacher.setDegree(teacherCustom.getDegree());
        teacher.setSelfIntroduction(teacherCustom.getSelfIntroduction());

        try {
            this.teacherService.save(teacher);
            responseBean.setResultCode(SUCCESS_CODE);
            responseBean.setResultMessage("申请讲师信息已提交，正在审核...");
        }catch (Exception e){
            responseBean.setResultMessage(e.getMessage());
            responseBean.setResultCode(ERROR_CODE);
        }

        return responseBean;
    }

    @RequestMapping(value = "/baseMessage", method = RequestMethod.GET)
    public String info() {
        return "personInfo";
    }

    @PostMapping(value = "/getInfo")
    @ResponseBody
    public StudentCustom getInfo(HttpSession session) {
        StudentCustom studentCustom = null;
        if (session.getAttribute(session.getId()) != null) {
            Student student = (Student) session.getAttribute(session.getId());
            studentCustom = new StudentCustom();

            studentCustom.setStudentId(student.getId());
            studentCustom.setStudentName(student.getStudentName());
            studentCustom.setStudentPassword(student.getStudentPassword());
            studentCustom.setStudentSex(student.getStudentSex());
            studentCustom.setStudentEmail(student.getStudentEmail());
            studentCustom.setPhotoStorageLocation(student.getPhotoStorageLocation());
        }

        return studentCustom;
    }

    @PostMapping(value = "/updateInfo")
    @ResponseBody
        public StudentCustom updateInfo(@RequestParam(value = "image", required = false) MultipartFile image,
                                    @RequestParam(value = "password", required = false) String password,
                                    @RequestParam(value = "email", required = false) String email,
                                    HttpSession session) {
        StudentCustom studentCustom = null;
        Student student = null;
        if (session.getAttribute(session.getId()) != null) {
            student = (Student) session.getAttribute(session.getId());
            if (password != null && !"".equals(password)) {
                student.setStudentPassword(password);
            }
            if (email != null && !"".equals(email)) {
                student.setStudentEmail(email);
            }
            if(!image.isEmpty()){
                String imageName = image.getOriginalFilename();
                String[] strings = imageName.split("\\.");          // .需要加转义字符
                imageName=imageName.replace(strings[0], Integer.toHexString(student.getId()).toString());

                String path=ClassUtils.getDefaultClassLoader().getResource("").getPath()
                        + "static/images/userLogo/";
                try {
                    // 文件保存路径
                   String savePath = path.substring(1,path.length());
                   File uploadPicPath=new File(savePath);
                    if(!uploadPicPath.exists()){
                        uploadPicPath.mkdir();
                    }
                    File uploadPic=new File(uploadPicPath+"/"+imageName);
                    if (uploadPic.exists()){
                        uploadPic.delete();
                    }
                    // 转存文件
                    image.transferTo(uploadPic);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                student.setPhotoStorageLocation("/images/userLogo/" + imageName);
            }
        }
        if (student != null) {
            this.studentService.update(student);
            studentCustom=new StudentCustom();
            studentCustom.setPhotoStorageLocation(student.getPhotoStorageLocation());
            studentCustom.setStudentEmail(student.getStudentEmail());
            studentCustom.setStudentSex(student.getStudentSex());
            studentCustom.setStudentPassword(student.getStudentPassword());
            studentCustom.setStudentName(student.getStudentName());
            studentCustom.setStudentId(student.getId());
        }

        return studentCustom;
    }
}
