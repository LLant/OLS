package org.gdpu.ols.service.impl;

import org.gdpu.ols.OlsApplication;
import org.gdpu.ols.model.ViewCoursewareDetail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

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
public class ViewCoursewareDetailServiceImplTest {

    @Autowired
    private ViewCoursewareDetailServiceImpl viewCoursewareDetailService;

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
    public void testget() throws Exception {
        List<ViewCoursewareDetail> list=this.viewCoursewareDetailService.findAll();
        System.out.println("ViewCoursewareDetailServiceImplTest.testget"+list.size());
        for (ViewCoursewareDetail viewCoursewareDetail:list){
            System.out.println(viewCoursewareDetail.toString());
        }
    }


} 
