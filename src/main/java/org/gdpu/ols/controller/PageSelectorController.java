package org.gdpu.ols.controller;

import org.gdpu.ols.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/OLS/pageSelector")
public class PageSelectorController extends BaseController {

    @GetMapping("/assessCourseware")
    public String getAssessCourseware(){
        return "assessCourseware";
    }

    @GetMapping("/assessTeacher")
    public String getAssessTeacher(){
        return "assessTeacher";
    }

    @GetMapping("/assessUser")
    public String managerUser(){
        return "assessUser";
    }

    @GetMapping("/managerTag")
    public String managerTag(){
        return "managerTag";
    }
}
