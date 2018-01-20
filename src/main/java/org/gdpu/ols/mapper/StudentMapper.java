package org.gdpu.ols.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.gdpu.ols.model.Student;

import java.util.List;

@Mapper
public interface StudentMapper {

    int getStudentCount();
    int addStudentBatch(List<Student> studentList);
    Student authenticStudent(String studentNameOrEmail);
    int updateStudentBatch(List<Student> studentList);
    Student getStudentByName(String studentName);
}
