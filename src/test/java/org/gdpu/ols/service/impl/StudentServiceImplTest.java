package org.gdpu.ols.service.impl;

import com.github.pagehelper.PageHelper;
import org.gdpu.ols.OlsApplication;
import org.gdpu.ols.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes =OlsApplication.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration// 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
public class StudentServiceImplTest {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Test
    public void getStudentCount(){

        int i=this.studentServiceImpl.getStudentCount();
        System.out.println(i);
    }

    @Test
    public void addStudentBatchTest(){
        List<Student> studentList=new ArrayList<Student>();
        Student student=null;
        for (int i = 10; i <50 ; i++) {
            student=new Student();
            student.setStudentPassword("daihao00"+i);
            student.setStudentEmail("daihao00"+i+"@163.com");
            if (i%2==0){
                student.setStudentSex("男");
            }else {
                student.setStudentSex("女");
            }
            student.setStudentName("代号00"+i);
            studentList.add(student);
        }
        System.out.println(this.studentServiceImpl.addStudentBatch(studentList));
    }

    @Test
    public void updateStudentBatchTest(){
        Student student=this.studentServiceImpl.getStudentByName("代号001");
        student.setStudentSex("女");
        student.setStudentEmail("daihao001@163.com");
        System.out.println(student.toString());
        List<Student> list=new ArrayList<Student>();
        list.add(student);
        this.studentServiceImpl.updateStudentBatch(list,true);
    }
    @Test
    public void authenticStudent(){
        System.out.println(this.studentServiceImpl.authenticStudent("代号001","daihao001"));
        System.out.println(this.studentServiceImpl.authenticStudent("daihao001@163.com","daihao001"));
    }

    @Test
    public void pageSelect(){
        PageHelper.startPage(10,10);
    }
}
