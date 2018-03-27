package org.gdpu.ols.controller;

import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@ResponseBody
@RequestMapping(value = "/OLS/student")
public class StudentController extends BaseController {

    @Resource
    private StudentService studentService;

}
