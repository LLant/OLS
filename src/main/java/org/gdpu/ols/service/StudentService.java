package org.gdpu.ols.service;

import org.gdpu.ols.model.Student;

import java.util.List;

public interface StudentService {

    int getStudentCount();
    int addStudentBatch(List<Student> studentList);
    int updateStudentBatch(List<Student> studentList,Boolean isTest);
    Student authenticStudent(String studentNameOrEmail,String studentPassword);
    Student getStudentByName(String studentName);
}
