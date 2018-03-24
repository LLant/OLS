package org.gdpu.ols.controller;

import org.gdpu.ols.bean.CourseBean;
import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.model.Courseware;
import org.gdpu.ols.model.File;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.model.Teacher;
import org.gdpu.ols.service.CoursewareService;
import org.gdpu.ols.service.FileService;
import org.gdpu.ols.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/OLS/course",method = RequestMethod.GET)
public class CoursewareController extends BaseController{

    @Resource
    private CoursewareService coursewareService;
    @Resource
    private FileService fileService;
    @Resource
    private TeacherService teacherService;

    @GetMapping("/")
    public String course(){
        return "course";
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
}
