package org.gdpu.ols.service.impl;

import org.gdpu.ols.core.AbstractService;
import org.gdpu.ols.mapper.StudentMapper;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.service.StudentService;
import org.gdpu.ols.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class StudentServiceImpl extends AbstractService<Student> implements StudentService{

    @Resource
    private StudentMapper studentMapper;

    @Override
    public int getStudentCount() {
        return this.studentMapper.getStudentCount();
    }

    @Override
    public int addStudentBatch(List<Student> studentList) {
        return this.addAndUpdateProcess(studentList,"add");
    }

    @Override
    public int updateStudentBatch(List<Student> studentList,Boolean isTest) {
        if (isTest){
            if(studentList!=null && !studentList.isEmpty()){
                return this.studentMapper.updateStudentBatch(studentList);
            }else {
                return 0;
            }
        }else{
            return this.addAndUpdateProcess(studentList,"update");
        }
    }

    @Override
    public Student authenticStudent(String studentNameOrEmail,String studentPassword) {

        if(studentNameOrEmail!=null || !"".equals(studentNameOrEmail)){
            Student student=null;
            student=this.studentMapper.authenticStudent(studentNameOrEmail);
            if (student!=null){
                if(student.getStudentPassword().equals(MD5Util.MD5Encode(studentPassword,""))){
                    return student;
                }
            }
        }
        return null;
    }

    @Override
    public Student getStudentByName(String studentName) {
        if(studentName==null || "".equals(studentName)){
            return null;
        }else {
            return this.studentMapper.getStudentByName(studentName);
        }
    }

    @Override
    public Boolean hasNameOrEmail(String studentName, String studentEmail) {
        Student student=null;
        student=this.studentMapper.selectStudentByName(studentName);
        if(student!=null)
            return true;
        student=this.studentMapper.selectStudentByEmail(studentEmail);
        if (student!=null)
            return true;
        return false;
    }

    @Override
    public void updateStudentStatus(String status, int id,int teacherId) {
        this.studentMapper.updateStudentStatus(status,id,teacherId);
    }

    private int addAndUpdateProcess(List<Student> studentList,String method){
        if (studentList!=null && !studentList.isEmpty()){
            Student student=null;
            List<Student> newStudentList=new ArrayList<Student>();
            for (int i = 0; i <studentList.size() ; i++) {
                student=studentList.get(i);
                student.setStudentPassword(MD5Util.MD5Encode(student.getStudentPassword(),""));
                newStudentList.add(student);
            }
            if (method!=null && !"".equals(method)){
                if("add".equals(method)){
                    return this.studentMapper.addStudentBatch(newStudentList);
                }
                if ("update".equals(method)){
                    return this.studentMapper.updateStudentBatch(newStudentList);
                }
            }
        }

        return 0;
    }
}
