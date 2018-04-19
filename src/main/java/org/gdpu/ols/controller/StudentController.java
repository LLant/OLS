package org.gdpu.ols.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.gdpu.ols.bean.MyPageRequest;
import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.common.ResponseBean;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.service.StudentService;
import org.gdpu.ols.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/OLS/student")
public class StudentController extends BaseController {

    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;

    @PostMapping("/getAllUser")
    @ResponseBody
    public ResponseBean getStudent(@RequestBody MyPageRequest myPageRequest){
        ResponseBean responseBean=new ResponseBean();
        PageHelper.startPage(Integer.parseInt(myPageRequest.getOffset()),
                Integer.parseInt(myPageRequest.getLimit()));
        Condition condition=new Condition(Student.class);
        condition.setOrderByClause("id desc");
        List<Student> studentList=this.studentService.findByCondition(condition);

        PageInfo pageInfo=new PageInfo(studentList);

        responseBean.setData(pageInfo);
        return responseBean;
    }

    @Transactional
    @ResponseBody
    @GetMapping("/delete/{userId:\\d{1,11}}")
    public String reject(@PathVariable int userId){
        Student student=this.studentService.findById(userId);
        if (!StringUtils.isEmpty(student.getTeacherId())){
            this.teacherService.deleteById(student.getTeacherId());
        }
        this.studentService.deleteById(userId);

        return SUCCESS;
    }
}
