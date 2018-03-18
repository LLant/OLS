package org.gdpu.ols.service.impl;

import org.gdpu.ols.OlsApplication;
import org.gdpu.ols.model.CourseType;
import org.gdpu.ols.model.Courseware;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * CoursewareServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 15, 2018</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OlsApplication.class)
@WebAppConfiguration
public class CourseTypeServiceImplTest {

    @Autowired
    private CourseTypeServiceImpl courseTypeService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addSingleCourseware(String coursewareName, String coursewareIntroduction, String coursewareTip, Integer author)
     */
    @Test
    public void testAddCourseType() throws Exception {
        CourseType courseType1=new CourseType();
        courseType1.setAspect("数据库");
        courseType1.setCategory("Oracle");
        this.courseTypeService.save(courseType1);
        CourseType courseType2=new CourseType();
        courseType2.setAspect("数据库");
        courseType2.setCategory("MySql");
        this.courseTypeService.save(courseType2);
        CourseType courseType3=new CourseType();
        courseType3.setAspect("前端开发");
        courseType3.setCategory("HTML/CSS");
        this.courseTypeService.save(courseType3);
    }


} 
