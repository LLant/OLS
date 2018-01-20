package org.gdpu.ols.service.impl;

import org.gdpu.ols.OlsApplication;
import org.gdpu.ols.model.Admin;
import org.gdpu.ols.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes =OlsApplication.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration// 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
public class AdminServiceImplTest {

    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @Test
    public void selectOneTest(){
        Admin admin=this.adminServiceImpl.selectOne("3");
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(admin.toString());
        System.out.println(df.format(admin.getLastestLoginDate()));
    }

    @Test
    public void addAdminBatchTest(){
        List<Admin> admins=new ArrayList<Admin>();
        Admin admin=new Admin();
        admin.setAdminName("admin");
        admin.setAdminPassword("admin");
        admins.add(admin);
//        admin.setAdminName("root");
//        admin.setAdminPassword("root");
//        admins.add(admin);
        System.out.println( this.adminServiceImpl.addAdminBatch(admins));
    }

}
