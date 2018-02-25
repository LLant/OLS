package org.gdpu.ols.mapper;

import org.gdpu.ols.model.Teacher;

import java.util.List;

public interface TeacherMapper extends org.gdpu.ols.core.Mapper<Teacher> {
    int addTeacherBatch(List<Teacher> list);
    Teacher authenticTeacher(String teacherNameOrEmail);
}
