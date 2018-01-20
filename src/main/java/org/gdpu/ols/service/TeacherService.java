package org.gdpu.ols.service;

import org.gdpu.ols.model.Teacher;

import java.util.List;

public interface TeacherService {
    int addTeacherBatch(List<Teacher> list);
    Teacher authenticTeacher(String teacherNameOrEmail,String teacherPassword);
}
