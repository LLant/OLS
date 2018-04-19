package org.gdpu.ols.service;

import org.gdpu.ols.core.Service;
import org.gdpu.ols.model.Student;
import java.util.List;

public interface StudentService extends Service<Student> {

    int getStudentCount();
    int addStudentBatch(List<Student> studentList);
    int updateStudentBatch(List<Student> studentList,Boolean isTest);
    Student authenticStudent(String studentNameOrEmail,String studentPassword);
    Student getStudentByName(String studentName);
    Boolean hasNameOrEmail(String studentName,String studentEmail);
    void updateStudentStatus(String status,int id,int teacherId);
}
