package org.gdpu.ols.mapper;

import org.apache.ibatis.annotations.Param;
import org.gdpu.ols.model.Teacher;

import java.util.List;

public interface TeacherMapper extends org.gdpu.ols.core.Mapper<Teacher> {
    int addTeacherBatch(List<Teacher> list);
    Teacher authenticTeacher(String teacherNameOrEmail);
    int addSingleTeacher(Teacher teacher);
    int updateTeacher(@Param("status")String status,@Param("id") Integer id);
}
