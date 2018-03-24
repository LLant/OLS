package org.gdpu.ols.controller;

import org.gdpu.ols.bean.MyPageRequest;
import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.model.RoleRelationship;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/OLS/rrs")
@Controller
public class RoleRelationshipController extends BaseController{

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