package org.gdpu.ols.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.gdpu.ols.bean.CourseBean;
import org.gdpu.ols.bean.MyPageRequest;
import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.common.ResponseBean;
import org.gdpu.ols.model.*;
import org.gdpu.ols.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/OLS/course",method = RequestMethod.GET)
public class CoursewareController extends BaseController{

    private static final String ERROR_CODE="9999";
    private static final String SUCCESS_CODE="8888";
    private static final String APPROVESTR="您发布的课程已经通过审核！";
    private static final String REJECTSTR="很遗憾您发布的课程未能通过审核!";
    private static final String N="N";
    private static final String APPROVE="通过审核";

    @Resource
    private CoursewareService coursewareService;
    @Resource
    private FileService fileService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentService studentService;
    @Resource
    private MessageService messageService;

    @GetMapping("/")
    public String course(){
        return "course";
    }

    @ResponseBody
    @PostMapping("/deleteCourse")
    public ResponseBean deleteCourse(@RequestBody Courseware courseware){

        ResponseBean responseBean=new ResponseBean();
        try {
            this.fileService.deleteByCourseware(courseware.getId());
            this.coursewareService.deleteById(courseware.getId());
            responseBean.setResultCode(SUCCESS_CODE);
        }catch (Exception e){
            responseBean.setResultCode(ERROR_CODE);
        }finally {
            return responseBean;
        }
    }

    @ResponseBody
    @PostMapping("/getAssessCourse")
    public ResponseBean getAssessCourse(@RequestBody MyPageRequest myPageRequest){

        PageHelper.startPage(Integer.parseInt(myPageRequest.getOffset()),
                Integer.parseInt(myPageRequest.getLimit()));
        ResponseBean responseBean=new ResponseBean();
        Condition condition=new Condition(Courseware.class);
        condition.createCriteria().andCondition("courseware_status='审核中'");
        condition.setOrderByClause("courseware_publish_date desc");
        List<Courseware> list=null;
        list=this.coursewareService.findByCondition(condition);

        PageInfo pageInfo=new PageInfo(list);

        responseBean.setResultCode(SUCCESS_CODE);
        responseBean.setData(pageInfo);
        return responseBean;
    }

    @ResponseBody
    @PostMapping("/getSelfCourse")
    public ResponseBean getCourse(@RequestBody MyPageRequest myPageRequest,HttpSession session){
        PageHelper.startPage(Integer.parseInt(myPageRequest.getOffset()),
                Integer.parseInt(myPageRequest.getLimit()));
        ResponseBean responseBean=new ResponseBean();
        Student student=null;
        if (session.getAttribute(session.getId())!=null){
            student= (Student) session.getAttribute(session.getId());
            if (student.getTeacherId()==null){
                responseBean.setResultCode(ERROR_CODE);
                return responseBean;
            }
        }
        Condition condition=new Condition(Courseware.class);
        condition.createCriteria().andCondition("author="+student.getTeacherId());
        condition.setOrderByClause("courseware_publish_date desc");
        List<Courseware> list=null;
        list=this.coursewareService.findByCondition(condition);

        PageInfo pageInfo=new PageInfo(list);

        responseBean.setResultCode(SUCCESS_CODE);
        responseBean.setData(pageInfo);
        return responseBean;
    }

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

    @Transactional
    @ResponseBody
    @GetMapping("/approve/{courseId:\\d{1,11}}/{author:\\d{1,11}}")
    public String approve(@PathVariable int courseId,@PathVariable int author,HttpSession session){

        int targetUser=this.getStudentId(author);
        if (targetUser==-1){
            return ERROR;
        }

        Message message=new Message();
        message.setTargetUser(targetUser);
        message.setContent(APPROVESTR);
        message.setIsRead(N);
        message.setDate(new Date());

        Admin admin= (Admin) session.getAttribute("admin"+session.getId());

        this.coursewareService.updateCourseware(APPROVE,courseId,admin.getId());
        this.messageService.save(message);
        return SUCCESS;
    }

    @Transactional
    @ResponseBody
    @GetMapping("/reject/{courseId:\\d{1,11}}/{author:\\d{1,11}}")
    public String reject(@PathVariable int courseId,@PathVariable int author){
        int targetUser=this.getStudentId(author);
        if (targetUser==-1){
            return ERROR;
        }

        Message message=new Message();
        message.setTargetUser(targetUser);
        message.setContent(REJECTSTR);
        message.setIsRead(N);
        message.setDate(new Date());

        this.coursewareService.deleteById(courseId);
        this.messageService.save(message);

        return SUCCESS;
    }

    private int getStudentId(int author){
        Condition condition=new Condition(Student.class);
        condition.createCriteria().andCondition("teacher_id="+author);
        List<Student> studentList=this.studentService.findByCondition(condition);
        if(!CollectionUtils.isEmpty(studentList)){
            return studentList.get(0).getId();
        }
        return -1;
    }
}
