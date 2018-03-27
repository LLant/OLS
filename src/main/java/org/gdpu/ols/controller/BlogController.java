package org.gdpu.ols.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.gdpu.ols.bean.MyPageRequest;
import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.common.ResponseBean;
import org.gdpu.ols.model.Blog;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.service.BlogService;
import org.gdpu.ols.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/OLS/blog")
public class BlogController extends BaseController {

    private static final String ERROR_CODE="9999";
    private static final String SUCCESS_CODE="8888";

    @Resource
    private BlogService blogService;
    @Resource
    private StudentService studentService;

    @GetMapping("/{id:\\d{1,11}}")
    public ModelAndView courseDetail(@PathVariable int id){
        ModelAndView modelAndView=new ModelAndView();

        return modelAndView;
    }

    @GetMapping("/")
    private String blog(){
        return "blog";
    }

    @ResponseBody
    @PostMapping("/deleteSingle")
    public ResponseBean delete(@RequestBody Blog blog){

        ResponseBean responseBean=new ResponseBean();
        try {
            this.blogService.deleteById(blog.getId());
            responseBean.setResultCode(SUCCESS_CODE);
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setResultCode(ERROR_CODE);
        }finally {
            return responseBean;
        }
    }

    @ResponseBody
    @PostMapping("/getBlog")
    public ResponseBean queryBlog(@RequestBody MyPageRequest myPageRequest){
        return this.getBlog(myPageRequest,"blog_title like '%"+myPageRequest.getCondition()+"%'");
    }

    @ResponseBody
    @PostMapping("/getSelfBlog")
    public ResponseBean querySelfBlog(HttpSession session,@RequestBody MyPageRequest myPageRequest){
        ResponseBean responseBean=new ResponseBean();
        Student student=null;
        if (session.getAttribute(session.getId())==null){
            responseBean.setResultCode(ERROR_CODE);
            return responseBean;
        }else {
            student= (Student) session.getAttribute(session.getId());
        }
        return this.getBlog(myPageRequest,"user="+student.getId());
    }

    /*@ResponseBody
    @PostMapping("/getSelfBlog")
    public Map<String,Object> querySelfBlog(HttpSession session,@RequestBody MyPageRequest myPageRequest){
        Map<String,Object> map=null;
        Student student=null;
        if (session.getAttribute(session.getId())==null){
            return map;
        }else {
            student= (Student) session.getAttribute(session.getId());
        }

        ResponseBean responseBean=this.getBlog(myPageRequest,"user="+student.getId());
        List<Blog> list=null;
        if (responseBean.getData() instanceof PageInfo){
            PageInfo<Blog> blogPageInfo= (PageInfo<Blog>) responseBean.getData();
            list=blogPageInfo.getList();
        }
        map=new HashMap<String,Object>();
        map.put("total",list.size());
        map.put("rows",list);
        return map;
    }*/

    @ResponseBody
    @PostMapping("/addBlog")
    public ResponseBean addBlog(@RequestBody Blog blog, HttpSession session){
        ResponseBean responseBean=new ResponseBean();
        if(session.getAttribute(session.getId())==null){
            responseBean.setResultCode(ERROR_CODE);
            return responseBean;
        }else {
            Student student= (Student) session.getAttribute(session.getId());
            blog.setUser(student.getId());
            blog.setAuthorName(student.getStudentName());
            blog.setBlogDate(new Date());
            this.blogService.save(blog);
            responseBean.setData(blog);
            responseBean.setResultCode(SUCCESS_CODE);
            return responseBean;
        }
    }

    private ResponseBean getBlog(MyPageRequest myPageRequest,String str){

        List<Blog> list=null;

        PageHelper.startPage(Integer.parseInt(myPageRequest.getOffset()),
                Integer.parseInt(myPageRequest.getLimit()));

        Condition condition=new Condition(Blog.class);
        if (!StringUtils.isEmpty(myPageRequest.getCondition())){
            condition.createCriteria().andCondition(str);
        }
        condition.setOrderByClause("blog_date desc");
        list=this.blogService.findByCondition(condition);

        PageInfo pageInfo=new PageInfo(list);
        ResponseBean responseBean=new ResponseBean();
        responseBean.setData(pageInfo);
        return responseBean;
    }
}
