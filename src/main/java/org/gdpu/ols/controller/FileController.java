package org.gdpu.ols.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.gdpu.ols.bean.MyPageRequest;
import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.common.ResponseBean;
import org.gdpu.ols.model.*;
import org.gdpu.ols.service.CoursewareService;
import org.gdpu.ols.service.FileService;
import org.gdpu.ols.service.MessageService;
import org.gdpu.ols.service.ViewCoursewareDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/OLS/courseware")
public class FileController extends BaseController{

    private static final String ERROR_CODE="9999";
    private static final String SUCCESS_CODE="8888";
    private static final String STATUS="通过审核";
    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource
    private FileService fileService;
    @Resource
    private CoursewareService coursewareService;
    @Resource
    private ViewCoursewareDetailService viewCoursewareDetailService;
    @Resource
    private MessageService messageService;


    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseBean updateCourseware(@RequestParam(value = "photo", required = false) MultipartFile photo,
                                         @RequestParam(value = "video", required = false) MultipartFile video,
                                         @RequestParam(value = "attach", required = false) MultipartFile attach,
                                         @RequestParam(value = "courseType", required = true) String courseType,
                                         @RequestParam(value = "courseName", required = true) String courseName,
                                         @RequestParam(value = "courseContent", required = true) String courseContent,
                                         @RequestParam(value = "courseIntroduction", required = true) String courseIntroduction,
                                         @RequestParam(value = "courseTip", required = true) String courseTip,
                                         HttpSession session) {
        Student user = null;
        ResponseBean responseBean=new ResponseBean();
        if (session.getAttribute(session.getId()) != null) {
            user = (Student) session.getAttribute(session.getId());
        }else {
            responseBean.setResultCode(ERROR_CODE);
            responseBean.setResultMessage("请先登录用户");
            return responseBean;
        }
        String currentTime = String.valueOf(System.currentTimeMillis());
        String savePath="/file/" + user.getId() + "/" + currentTime + "/";
        Courseware courseware=null;
        courseware=this.coursewareService.addSingleCourseware(courseName,courseIntroduction,courseTip,
                "审核中",user.getTeacherId(),courseType,
                savePath+photo.getOriginalFilename(),courseContent);
        int coursewareId=0;
        if(courseware!=null){
            coursewareId=courseware.getId();
        }
        try {
            if (!photo.isEmpty()) {
                this.saveFile(photo, savePath,user.getId(),coursewareId);
            }
            if (!video.isEmpty()) {
                this.saveFile(video, savePath,user.getId(),coursewareId);
            }
            if (attach!=null) {
                this.saveFile(attach, savePath,user.getId(),coursewareId);
            }
        } catch (IOException e) {
            e.printStackTrace();
            responseBean.setResultCode(ERROR_CODE);
            responseBean.setResultMessage("文件上传错误");
        }
        responseBean.setResultCode(SUCCESS_CODE);
        responseBean.setResultMessage("文件上传成功");

        this.setMessage(user.getId(),"您的课件已成功上传，正在等待管理员审核...");

        return responseBean;
    }

    private void saveFile(MultipartFile file, String path,int author,int courseware) throws IOException {
        String systemPath = ClassUtils.getDefaultClassLoader().getResource("").getPath()
                + "static";
        // 文件保存路径
        String savePath = systemPath.substring(1, systemPath.length())+path;
        java.io.File uploadPath = new java.io.File(savePath);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        java.io.File uploadPic = new java.io.File(uploadPath + "/" + file.getOriginalFilename());
        if (uploadPic.exists()) {
            uploadPic.delete();
        }
        // 转存文件
        file.transferTo(uploadPic);

        File file1=new File();
        file1.setAuthor(author);
        file1.setCourseware(courseware);
        file1.setStorageLocation(path+file.getOriginalFilename());
        file1.setFileName(file.getOriginalFilename());

        String[] names=file.getOriginalFilename().split("\\.");
        file1.setFileType(names[1]);
        if("mp4".equals(names[1]) || "avi".equals(names[1])){
            file1.setMainFile(1);
        }else {
            if ("rar".equals(names[1]) || "zip".equals(names[1])){
                file1.setMainFile(2);
            }else {
                file1.setMainFile(0);
            }
        }
        file1.setPublishDate(new Date());
        this.fileService.save(file1);
    }

    @ResponseBody
    @PostMapping(value = "/getCoursewareInfo")
    public ResponseBean getCoursewareByPageAndCondition(@RequestBody MyPageRequest myPageRequest){

        PageHelper.startPage(Integer.parseInt(myPageRequest.getOffset()),
                Integer.parseInt(myPageRequest.getLimit()));
        List<ViewCoursewareDetail> list=null;

        StringBuilder query=new StringBuilder();
        query.append("courseware_status='"+STATUS+"'");
        if(!StringUtils.isEmpty(myPageRequest.getCondition())){
            query.append(" and courseware_name like '%"+myPageRequest.getCondition()+"%' ");
        }
        if(!StringUtils.isEmpty(myPageRequest.getAspect())){
            query.append("and aspect = '"+myPageRequest.getAspect()+"' ");
        }
        if (!StringUtils.isEmpty(myPageRequest.getCategory())){
            query.append("and category = '"+myPageRequest.getCategory()+"'");
        }

        Condition condition=new Condition(Courseware.class);
        condition.createCriteria().andCondition(query.toString());
        condition.setOrderByClause("courseware_publish_date desc");
        list=this.viewCoursewareDetailService.findByCondition(condition);

        PageInfo pageInfo=new PageInfo(list);
        ResponseBean responseBean=new ResponseBean();
        responseBean.setData(pageInfo);
        responseBean.setResultCode(SUCCESS_CODE);
        return responseBean;
    }

    private void setMessage(Integer targetUser,String content){
       Message message=new Message();
       message.setContent(content);
       message.setDate(new Date());
       message.setIsRead("N");
       message.setTargetUser(targetUser);
       this.messageService.save(message);
    }
}
