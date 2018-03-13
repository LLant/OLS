package org.gdpu.ols.service.impl;

import org.gdpu.ols.core.AbstractService;
import org.gdpu.ols.mapper.CoursewareMapper;
import org.gdpu.ols.model.Courseware;
import org.gdpu.ols.service.CoursewareService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class CoursewareServiceImpl extends AbstractService<Courseware> implements CoursewareService {

    @Resource
    private CoursewareMapper coursewareMapper;
    @Override
    public int addSingleCourseware(String coursewareName, String coursewareIntroduction, String coursewareTip, Integer author) {
        Courseware courseware=new Courseware();
        courseware.setCoursewarePublishDate(new Date());
        courseware.setCoursewareStatus("审核中");
        courseware.setCoursewareName(coursewareName);
        courseware.setCoursewareIntroduction(coursewareIntroduction);
        courseware.setCoursewareTip(coursewareTip);
        courseware.setAuthor(author);

        return this.coursewareMapper.addSingleCourseware(courseware);
    }
}
