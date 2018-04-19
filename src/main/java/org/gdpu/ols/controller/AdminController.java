package org.gdpu.ols.controller;

import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.model.Admin;
import org.gdpu.ols.service.AdminService;
import org.gdpu.ols.util.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/OLS/admin")
public class AdminController extends BaseController{

    @Resource
    private AdminService adminService;

    @GetMapping(value = "/")
    public String home(){
        return "adminLogin";
    }

    @GetMapping(value = "/index")
    public String index(){
        return "adminHome";
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public String login(@RequestBody Admin admin, HttpSession session){
        Condition condition=new Condition(Admin.class);
        condition.createCriteria().andCondition("admin_name='"+admin.getAdminName()+
                "' and admin_password='"+
                MD5Util.MD5Encode(admin.getAdminPassword(),"")+"'");
        List<Admin> admins=this.adminService.findByCondition(condition);
        if (CollectionUtils.isEmpty(admins)){
            return "9999";
        }else {
            Admin admin1=admins.get(0);
            session.setAttribute("admin"+session.getId(),admin1);
            return "8888";
        }
    }

    @PostMapping("/loginOut")
    @ResponseBody
    public String loginOut(HttpSession session){
        session.removeAttribute("admin"+session.getId());
        return "8888";
    }
}
