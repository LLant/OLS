package org.gdpu.ols.mapper;

import org.gdpu.ols.model.Student;

import java.util.List;

public interface StudentMapper extends org.gdpu.ols.core.Mapper<Student> {

    int getStudentCount();
    int addStudentBatch(List<Student> studentList);
    Student authenticStudent(String studentNameOrEmail);
    Student selectStudentByName(String studentName);
    Student selectStudentByEmail(String studentEmail);
    int updateStudentBatch(List<Student> studentList);
    Student getStudentByName(String studentName);
}
