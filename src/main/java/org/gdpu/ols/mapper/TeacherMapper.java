package org.gdpu.ols.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.model.Teacher;

import java.util.List;

@Mapper
public interface TeacherMapper {
    int addTeacherBatch(List<Teacher> list);
    Teacher authenticTeacher(String teacherNameOrEmail);
}
