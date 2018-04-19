package org.gdpu.ols.controller;

import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.model.CourseType;
import org.gdpu.ols.service.CourseTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@RequestMapping(value = "/OLS/courseType")
@Controller
public class CourseTypeController extends BaseController{

    @Resource
    private CourseTypeService courseTypeService;

    @ResponseBody
    @PostMapping("addTag")
    public String addAspect(@RequestBody CourseType courseType){
        this.courseTypeService.save(courseType);
        return SUCCESS;
    }

    @ResponseBody
    @PostMapping(value = "/getCategory")
    public List<CourseType> getCategory(){

        List<CourseType> courseTypeList=null;

        courseTypeList=this.courseTypeService.findAll();

        return courseTypeList;
    }

    @ResponseBody
    @PostMapping(value = "/getTag")
    public Map<String,List<String>> getTag(){
        List<CourseType> list=this.courseTypeService.findAll();

        Map<String,List<String>> map=null;
        if(!CollectionUtils.isEmpty(list)){
            map=new HashMap<String, List<String>>();
            List<String> stringList=null;
            for (CourseType courseType:list){
                if(map.containsKey(courseType.getAspect())){
                    stringList=map.get(courseType.getAspect());
                }else {
                    stringList=new ArrayList<String>();
                }
                stringList.add(courseType.getCategory());
                map.put(courseType.getAspect(),stringList);
                stringList=null;
            }
        }
        return map;
    }

    @ResponseBody
    @PostMapping(value = "/getAll")
    public Map<String,List<String>> getCourseType(){
        List<CourseType> list=this.courseTypeService.findAll();

        Map<String,List<String>> map=null;
        if(!CollectionUtils.isEmpty(list)){
            map=new HashMap<String, List<String>>();
            List<String> aspectList=null;
            List<String> categoryList=null;
            for (CourseType courseType:list){
                aspectList=new ArrayList<String>();
                if(map.containsKey("aspect")){
                    aspectList=map.get("aspect");
                }
                if(!aspectList.contains(courseType.getAspect())){
                    aspectList.add(courseType.getAspect());
                }
                map.put("aspect",aspectList);

                categoryList=new ArrayList<String>();
                if(map.containsKey("category")){
                    categoryList=map.get("category");
                }
                if(!categoryList.contains(courseType.getCategory())){
                    categoryList.add(courseType.getCategory());
                }
                map.put("category",categoryList);
            }
        }

        return map;
    }
}
