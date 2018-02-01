package org.gdpu.ols.controller;

import org.gdpu.ols.bean.UserBean;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


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

    @PostMapping("/sendCode")
    public void sendCode(@RequestParam(defaultValue = "") String code){
        logger.info(code);
    }

    @GetMapping("/course")
    public String course(){
        return "course";
    }
    @GetMapping("/course/{id:\\d{6}}")
    public String courseDetail(@PathVariable int id){
        return "video";
    }

    @PostMapping("/register")
    public @ResponseBody ResponseBean register(@RequestBody UserBean userBean,HttpSession session){
        responseBean=new ResponseBean();
        if("".equals(userBean.getUsername()) || "".equals(userBean.getPassword()) ||
                "".equals(userBean.getEmail())){
            responseBean.setResultCode(ERROR_CODE);
            responseBean.setResultMessage("用户名、密码或邮箱不能为空");
        }else{
            if(this.studentService.hasNameOrEmail(userBean.getUsername(),userBean.getEmail())){
                responseBean.setResultCode(ERROR_CODE);
                responseBean.setResultMessage("该用户名或邮箱已被注册");
            }else {
                Student student=new Student();
                student.setStudentName(userBean.getUsername());
                student.setStudentPassword(userBean.getPassword());
                student.setStudentEmail(userBean.getEmail());
                student.setStudentSex("女");
                List<Student> lists=new ArrayList<Student>();
                lists.add(student);
                int i=this.studentService.addStudentBatch(lists);
                logger.info("i=",i);
                if(i==1){
                    student.setStudentId(
                            this.studentService.authenticStudent(student.getStudentName(),
                                    student.getStudentPassword()).getStudentId());
                    responseBean.setResultCode(SUCCESS_CODE);
                    responseBean.setResultMessage("用户注册成功");
                    responseBean.setData(student);
                    session.setAttribute("user",student);
                }else {
                    responseBean.setResultCode(ERROR_CODE);
                    responseBean.setResultMessage("注册失败");
                }
            }

        }

        return responseBean;
    }
    @PostMapping("/login")
    @ResponseBody
    public ResponseBean login(@RequestBody UserBean userBean, HttpSession session) {

//        System.out.println(userBean.getUsername()+","+userBean.getPassword());
        responseBean = new ResponseBean();
        if ("".equals(userBean.getUsername()) || "".equals(userBean.getPassword())) {
            responseBean.setResultCode(ERROR_CODE);
            responseBean.setResultMessage("用户名或密码不能为空");
        } else {
            switch (userBean.getRole()){
                case 1:{
                    Student student=null;
                    student=this.studentService.authenticStudent(userBean.getUsername(),userBean.getPassword());
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
                    teacher=this.teacherService.authenticTeacher(userBean.getUsername(),userBean.getPassword());
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
        System.out.println(responseBean.toString());
        return responseBean;
    }
}
