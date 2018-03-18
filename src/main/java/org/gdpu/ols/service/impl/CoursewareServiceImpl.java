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
    public Courseware addSingleCourseware(String coursewareName, String coursewareIntroduction,
                                          String coursewareTip,String coursewareStatus, Integer author,
                                          String courseType,String coursewarePhotoLocation) {
        Courseware courseware=new Courseware();
        courseware.setCoursewarePublishDate(new Date());
        courseware.setCoursewareName(coursewareName);
        courseware.setCoursewareStatus(coursewareStatus);
        courseware.setCoursewareIntroduction(coursewareIntroduction);
        courseware.setCoursewareTip(coursewareTip);
        courseware.setAuthor(author);
        courseware.setCoursewareType(Integer.parseInt(courseType));
        courseware.setCoursewarePhotoLocation(coursewarePhotoLocation);

        this.coursewareMapper.addSingleCourseware(courseware);
        return courseware;
    }
}
