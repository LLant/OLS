package org.gdpu.ols.controller;

import org.gdpu.ols.bean.MyPageRequest;
import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.common.ResponseBean;
import org.gdpu.ols.model.RoleRelationship;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.service.RoleRelationshipService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/OLS/rrs")
@Controller
public class RoleRelationshipController extends BaseController{

    @Resource
    private RoleRelationshipService roleRelationshipService;

    @ResponseBody
    @PostMapping("/follow")
    public String add(@RequestBody RoleRelationship roleRelationship, HttpSession session){
        Student student= (Student) session.getAttribute(session.getId());
        roleRelationship.setStudent(student.getId());
        this.roleRelationshipService.save(roleRelationship);
        return "8888";
    }

    @ResponseBody
    @PostMapping("/disFollow")
    public String disFollow(@RequestBody RoleRelationship roleRelationship, HttpSession session){
        Student student= (Student) session.getAttribute(session.getId());
        this.roleRelationshipService.disFollow(student.getId(),roleRelationship.getTeacher());
        return "8888";
    }

    @ResponseBody
    @PostMapping(value = "/get")
    public Map<String,Object> getRelationship(@RequestBody(required = false) MyPageRequest myPageRequest){
        List<RoleRelationship> list=new ArrayList<RoleRelationship>();
        RoleRelationship r1=new RoleRelationship();
        r1.setId(1);
        r1.setStudent(11);
        r1.setTeacher(111);
        RoleRelationship r2=new RoleRelationship();
        r2.setId(2);
        r2.setStudent(22);
        r2.setTeacher(222);
        list.add(r1);
        list.add(r2);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("total",list.size());
        map.put("rows",list);
        return map;
    }
}