package org.gdpu.ols.service;

import org.gdpu.ols.core.Service;
import org.gdpu.ols.model.Teacher;

import java.util.List;

public interface TeacherService extends Service<Teacher> {
    int addTeacherBatch(List<Teacher> list);
    /*Teacher authenticTeacher(String teacherNameOrEmail,String teacherPassword);*/
    Teacher addSingleTeacher(Teacher teacher);
}
