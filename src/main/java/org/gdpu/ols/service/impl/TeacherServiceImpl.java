package org.gdpu.ols.service.impl;

import org.gdpu.ols.core.AbstractService;
import org.gdpu.ols.mapper.TeacherMapper;
import org.gdpu.ols.model.Teacher;
import org.gdpu.ols.service.TeacherService;
import org.gdpu.ols.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class TeacherServiceImpl extends AbstractService<Teacher> implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public int addTeacherBatch(List<Teacher> list) {
        if (list!=null && !list.isEmpty()){
            return this.teacherMapper.addTeacherBatch(list);
        }else{
            return 0;
        }
    }

    @Override
    public Teacher addSingleTeacher(Teacher teacher) {
        this.teacherMapper.addSingleTeacher(teacher);
        return teacher;
    }

 /*   @Override
    public Teacher authenticTeacher(String teacherNameOrEmail,String teacherPassword) {
        if (teacherNameOrEmail!=null && !"".equals(teacherNameOrEmail)){
            Teacher teacher=null;
            teacher=this.teacherMapper.authenticTeacher(teacherNameOrEmail);
            if(teacher!=null){
                if (teacher.getTeacherPassword().equals(MD5Util.MD5Encode(teacherPassword,""))){
                    return teacher;
                }
            }
        }
        return null;
    }*/
}
