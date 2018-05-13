package org.gdpu.ols.controller;

import org.gdpu.ols.bean.CourseBean;
import org.gdpu.ols.bean.UserBean;
import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.common.ResponseBean;
import org.gdpu.ols.model.*;
import org.gdpu.ols.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value ="/OLS")
public class HomeController extends BaseController {

    private static final Logger logger= LoggerFactory.getLogger(HomeController.class);
    private static final String ERROR_CODE="9999";
    private static final String SUCCESS_CODE="8888";
    private static final String DEFAULTPHOTO="/images/defaultPersonHead.jpg";


    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentService studentService;
    @Resource
    private ViewCoursewareDetailService viewCoursewareDetailService;
    @Resource
    private CoursewareService coursewareService;
    @Resource
    private FileService fileService;

    private ResponseBean responseBean;

    @GetMapping("/{id:\\d{1,11}}")
    public ModelAndView courseDetail(@PathVariable int id, HttpSession session){

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("video");
        Courseware courseware=null;
        courseware=this.coursewareService.findById(id);
        Condition condition=new Condition(File.class);
        condition.createCriteria().andCondition("courseware="+courseware.getId()+" and main_file=1");
        List<File> files=null;
        files=this.fileService.findByCondition(condition);

        CourseBean courseBean=new CourseBean();
        for (File file:files){
            if(file.getStorageLocation()!=null){
                switch (file.getMainFile()){
                    case 1:courseBean.setVideoLocation(file.getStorageLocation());break;
                    case 2:courseBean.setAttachLocation(file.getStorageLocation());break;
                }

            }
        }
        Teacher teacher=this.teacherService.findById(courseware.getAuthor());
        courseBean.setAuthor(courseware.getAuthor());
        courseBean.setAuthorRealName(teacher.getRealName());
        courseBean.setAuthorDegree(teacher.getDegree());
        courseBean.setAuthorPhotoLocation(teacher.getPhotoStorageLocation());
        courseBean.setCoursewareContent(courseware.getCoursewareContent());
        courseBean.setCoursewareIntroduction(toList(courseware.getCoursewareIntroduction()));
        courseBean.setCoursewareName(courseware.getCoursewareName());
        courseBean.setCoursewarePublishDate(courseware.getCoursewarePublishDate());
        courseBean.setCoursewareTip(toList(courseware.getCoursewareTip()));
        courseBean.setId(courseware.getId());
        modelAndView.addObject("courseware",courseBean);
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("home");

        Condition condition1=new Condition(Teacher.class);
        condition1.createCriteria().andCondition("recommend='Y'");
        List<Teacher> teachers=this.teacherService.findByCondition(condition1);
        modelAndView.addObject("teachers",teachers);

        Condition condition2=new Condition(ViewCoursewareDetail.class);
        condition2.createCriteria().andCondition("recommend='Y'");
        List<ViewCoursewareDetail> recommendCourses=this.viewCoursewareDetailService.findByCondition(condition2);

        modelAndView.addObject("firstRecommend",recommendCourses.get(0));
        modelAndView.addObject("secondRecommend",recommendCourses.get(1));
        modelAndView.addObject("thirdRecommend",recommendCourses.get(2));

        List<ViewCoursewareDetail> viewCoursewareDetails=this.viewCoursewareDetailService.getTop5();
        modelAndView.addObject("coursewares",viewCoursewareDetails);

        return modelAndView;
    }

    @GetMapping("/loginOut")
    @ResponseBody
    public String loginOut(HttpSession session){            //必须有返回类型，否则themeleaf会报错
        Date nowTime=new Date();
        Date loginTime=null;
        Student student=null;
        Long learningTime=null;
        if(session.getAttribute(session.getId())!=null){
            loginTime= (Date) session.getAttribute(session.getId()+"learningTime");
            learningTime=nowTime.getTime()-loginTime.getTime();
            student= (Student) session.getAttribute(session.getId());
            if(StringUtils.isEmpty(student.getLearningTime())){
                student.setLearningTime(String.valueOf(learningTime));
            }else {
                learningTime+=Long.parseLong(student.getLearningTime());
                student.setLearningTime(String.valueOf(learningTime));
            }
            session.removeAttribute(session.getId());
        }
        this.studentService.update(student);
        //session.getAttribute(session.getId())         session中不存在此属性会报错
        return SUCCESS_CODE;
    }
    @ResponseBody
    @PostMapping("/isLogin")
    public String isLogin(HttpSession session){
        String isLogin=null;
        if(session.getAttribute(session.getId())!=null){
            return ((Student)session.getAttribute(session.getId())).getStudentName();
        }else{
            return null;
        }
    }

    @PostMapping("/sendCode")
    @ResponseBody
    public String sendCode(@RequestParam(defaultValue = "") String code){
        logger.info(code);
        return SUCCESS_CODE;
    }






    @PostMapping("/register")
    @Transactional
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
                student.setStatus("S");
                student.setPhotoStorageLocation(DEFAULTPHOTO);
                List<Student> lists=new ArrayList<Student>();
                lists.add(student);
                int i=this.studentService.addStudentBatch(lists);
                logger.info("i="+i);
                if(i==1){
                    logger.info("ssss"+userBean.getUsername()+userBean.getPassword());
                    Student user=null;
                    user=this.studentService.authenticStudent(userBean.getUsername(),userBean.getPassword());
                    if(user!=null){
                        responseBean.setResultCode(SUCCESS_CODE);
                        responseBean.setResultMessage("学生用户登录成功");
                        responseBean.setData(user);
                        session.setAttribute(session.getId(),user);
                        session.setAttribute(session.getId()+"learningTime",new Date());
                        session.setMaxInactiveInterval(60*30);
                    }else{
                        responseBean.setResultCode(ERROR_CODE);
                        responseBean.setResultMessage("学生用户登录失败");
                    }
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
                        session.setAttribute(session.getId(),student);
                        session.setAttribute(session.getId()+"learningTime",new Date());
                        session.setMaxInactiveInterval(60*30);
                    }else {
                        responseBean.setResultCode(ERROR_CODE);
                        responseBean.setResultMessage("学生用户登录失败");
                    }
                }break;
                /*case 2:{
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
                }*/
            }
        }
        System.out.println(responseBean.toString());
        return responseBean;
    }

//    @PostMapping(value = "/setCondition")
//    @ResponseBody
//    public String setCondition(@RequestBody String condition,HttpSession session){
//        session.setAttribute("condition",condition);
//        return "success";
//    }
//
//    @PostMapping(value = "/getCondition")
//    @ResponseBody
//    public String getCondition(HttpServletRequest request,HttpSession session){
//
//        String condition="";
//        if(session.getAttribute("condition")!=null){
//            condition= (String) session.getAttribute("condition");
//            session.removeAttribute("condition");
//        }
//        return condition;
//    }

    private List<String> toList(String str){
        List<String> stringList=null;
        String[] strArray=str.split(";");
        if (strArray.length>0){
            int i=1;
            stringList=new ArrayList<String>();
            for (String string:strArray){
                stringList.add(i+"、"+string);
                i++;
            }
        }
        return stringList;
    }
}
