package org.gdpu.ols.controller;

import org.gdpu.ols.bean.StudentCustom;
import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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

    @Resource
    private StudentService studentService;

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
                                    HttpSession session, HttpServletRequest request) {
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
            if (image != null) {
                String imageName = image.getOriginalFilename();
                String[] strings = imageName.split(".");
                imageName.replace(strings[0], student.getStudentName().getBytes().toString());

                String realPath = request.getServletContext().getRealPath("/");
                logger.info("realPath", realPath);

                if (!image.isEmpty()) {
                    String path = realPath + "src\\main\\resources\\static\\images\\userLogo";
                    File targetFile = new File(path);

                    if (!targetFile.exists()) {
                        targetFile.mkdir();
                    }
                    try {
                        // 文件保存路径
                        String savePath = path + imageName;
                        // 转存文件
                        image.transferTo(new File(savePath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    student.setPhotoStorageLocation("/images/userLogo/" + imageName);
                }
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
