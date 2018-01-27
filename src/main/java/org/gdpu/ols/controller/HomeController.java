package org.gdpu.ols.controller;

import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.common.ResponseBean;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.model.Teacher;
import org.gdpu.ols.service.StudentService;
import org.gdpu.ols.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value ="/OIS")
public class HomeController extends BaseController {

    private static final Logger logger= LoggerFactory.getLogger(HomeController.class);
    private static final String ERROR_CODE="9999";
    private static final String SUCCESS_CODE="8888";


    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentService studentService;

    private ResponseBean responseBean;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @ResponseBody
    @PostMapping("/isLogin")
    public Boolean isLogin(HttpSession session){
        if(session.getAttribute("user")!=null){
            return true;
        }else{
            return false;
        }
    }

    @GetMapping("/course")
    public String course(){
        return "course";
    }
    @GetMapping("/course/{id:\\d{6}}")
    public String courseDetail(@PathVariable int id){
        return "video";
    }
    @GetMapping("/login")
    @ResponseBody
    public ResponseBean login(@RequestParam(defaultValue = "") String usernameOrEmail,
                         @RequestParam(defaultValue = "") String password,
                         @RequestParam(defaultValue = "1") Integer role, HttpSession session) {

        System.out.println(usernameOrEmail+","+password);
        responseBean = new ResponseBean();
        if ("".equals(usernameOrEmail) || "".equals(password)) {
            responseBean.setResultCode(ERROR_CODE);
            responseBean.setResultMessage("用户名或密码不能为空");
        } else {
            switch (role){
                case 1:{
                    Student student=null;
                    student=this.studentService.authenticStudent(usernameOrEmail,password);
                    if(student!=null){
                        responseBean.setResultCode(SUCCESS_CODE);
                        responseBean.setResultMessage("学生用户登录成功");
                        responseBean.setData(student);
                        session.setAttribute("user",student);
                    }else {
                        responseBean.setResultCode(ERROR_CODE);
                        responseBean.setResultMessage("学生用户登录失败");
                    }
                }break;
                case 2:{
                    Teacher teacher=null;
                    teacher=this.teacherService.authenticTeacher(usernameOrEmail,password);
                    if (teacher!=null){
                        responseBean.setResultCode(SUCCESS_CODE);
                        responseBean.setResultMessage("教师用户登录成功");
                        responseBean.setData(teacher);
                        session.setAttribute("user",teacher);
                    }else {
                        responseBean.setResultCode(ERROR_CODE);
                        responseBean.setResultMessage("教师用户登录失败");
                    }
                }break;
                default:{
                    responseBean.setResultCode(ERROR_CODE);
                    responseBean.setResultMessage("不存在该角色");
                }
            }
        }
        //System.out.println(responseBean.toString());
        return responseBean;
    }
}
