package org.gdpu.ols.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.gdpu.ols.bean.CourseBean;
import org.gdpu.ols.bean.MyPageRequest;
import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.common.ResponseBean;
import org.gdpu.ols.model.Courseware;
import org.gdpu.ols.model.File;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.model.Teacher;
import org.gdpu.ols.service.CoursewareService;
import org.gdpu.ols.service.FileService;
import org.gdpu.ols.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/OLS/course",method = RequestMethod.GET)
public class CoursewareController extends BaseController{

    private static final String ERROR_CODE="9999";
    private static final String SUCCESS_CODE="8888";

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
}
