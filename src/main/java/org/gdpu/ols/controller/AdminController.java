package org.gdpu.ols.controller;

import org.gdpu.ols.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/OLS/admin")
public class AdminController extends BaseController{

    @GetMapping(value = "/login")
    public String login(){
        return "adminLogin";
    }
}
