package org.gdpu.ols.controller;

import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.model.Message;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.service.MessageService;
import org.gdpu.ols.service.StudentService;
import org.gdpu.ols.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/OLS/teacher")
public class TeacherController extends BaseController{

    private static final String APPROVE="通过审核";
    private static final String APPROVESTR="您已通过讲师申请！";
    private static final String REJECTSTR="很遗憾您的资质未到要求，未能通过审核";
    private static final String N="N";
    private static final String STUDENTSTATUS="S";
    private static final String TEACHERSTATUS="T";

    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentService studentService;
    @Resource
    private MessageService messageService;

    @Transactional
    @ResponseBody
    @GetMapping("/approve/{teacherId:\\d{1,11}}")
    public String approve(@PathVariable int teacherId){

        int targetUser=this.getStudentId(teacherId);
        if (targetUser==-1){
            return ERROR;
        }

        Message message=new Message();
        message.setTargetUser(targetUser);
        message.setContent(APPROVESTR);
        message.setIsRead(N);
        message.setDate(new Date());

        this.teacherService.updateTeacher(APPROVE,teacherId);
        this.studentService.updateStudentStatus(TEACHERSTATUS,targetUser,teacherId);
        this.messageService.save(message);
        return SUCCESS;
    }

    @Transactional
    @ResponseBody
    @GetMapping("/reject/{teacherId:\\d{1,11}}")
    public String reject(@PathVariable int teacherId){
        int targetUser=this.getStudentId(teacherId);
        if (targetUser==-1){
            return ERROR;
        }

        Message message=new Message();
        message.setTargetUser(targetUser);
        message.setContent(REJECTSTR);
        message.setIsRead(N);
        message.setDate(new Date());

        this.studentService.updateStudentStatus(STUDENTSTATUS,targetUser,-1);
        this.teacherService.deleteById(teacherId);
        this.messageService.save(message);

        return SUCCESS;
    }

    private int getStudentId(int teacherId){
        Condition condition=new Condition(Student.class);
        condition.createCriteria().andCondition("teacher_id="+teacherId);
        List<Student> studentList=this.studentService.findByCondition(condition);
        if(!CollectionUtils.isEmpty(studentList)){
            return studentList.get(0).getId();
        }
        return -1;
    }
}
